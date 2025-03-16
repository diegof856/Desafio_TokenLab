# `Cadastrol.jsx`

## 1. Importações
```javascript
import { Link, useNavigate } from 'react-router-dom';
import "./Login.css";
import "./Cadastro.css";
import { useState } from 'react';
```
- `Link` e `useNavigate` são importados do `react-router-dom` para navegação entre páginas.
- Os arquivos CSS `Login.css` e `Cadastro.css` são importados para estilização.
- `useState` é importado do React para manipular estados dentro do componente.

## 2. Declaração da URL da API
```javascript
const url = "http://localhost:8080/v1/usuarios";
```
- Define a URL da API onde os dados de cadastro serão enviados.

## 3. Definição do Componente Cadastro
```javascript
const Cadastro = () => {
```
- Declara o componente funcional `Cadastro`.

## 4. Declaração dos Estados
```javascript
const [nome, setNome] = useState("");
const [email, setEmail] = useState("");
const [senha, setSenha] = useState("");
const [errorMessage, setErrorMessage] = useState("");
const navigate = useNavigate();
```
- `nome`, `email` e `senha` armazenam os dados do formulário.
- `errorMessage` guarda mensagens de erro, caso ocorram.
- `useNavigate()` é usado para redirecionamento após o cadastro.

## 5. Função de Submissão do Formulário
```javascript
const handleSubmit = async (e) => {
    e.preventDefault();
    setErrorMessage("");
    try {
        const usuarioRequestDTO = {
            nome,
            email,
            senha,
        };
```
- `handleSubmit` é acionada ao enviar o formulário.
- `e.preventDefault()` previne o recarregamento da página.
- Define um objeto `usuarioRequestDTO` com os dados do usuário.

### 5.1 Envio da Requisição para a API
```javascript
const res = await fetch(url, {
    method: "POST",
    headers: {
        "Content-Type": "application/json",
    },
    body: JSON.stringify(usuarioRequestDTO),
});
```
- A requisição é enviada via `fetch` com o método `POST`.
- `headers` define o tipo de conteúdo como JSON.
- O corpo da requisição é convertido para JSON.

### 5.2 Tratamento de Erros
```javascript
if (!res.ok) {
    const errorData = await res.json();
    setNome("")
    setEmail("")
    setSenha("")
    throw new Error(errorData.mensagem);
}
```
- Se a resposta não for bem-sucedida, os campos são limpos e uma mensagem de erro é definida.

### 5.3 Redirecionamento e Limpeza dos Campos
```javascript
setNome("")
setEmail("")
setSenha("")
navigate("/");
```
- Se o cadastro for bem-sucedido, os campos são resetados.
- O usuário é redirecionado para a página inicial (`/`).

### 5.4 Captura de Erros
```javascript
} catch (error) {
    setErrorMessage(error.message);
}
```
- Caso ocorra um erro, a mensagem é armazenada em `errorMessage`.

## 6. Estrutura do JSX (Interface do Formulário)
```javascript
return (
    <div className="login">
        <div className="container_login">
        <img src="/login_cadastro_img.jpg" alt="imagem ao lado do login" />
```
- O JSX retorna a estrutura da tela de cadastro.
- A classe `login` define o container principal.
- A imagem ilustrativa é carregada.

### 6.1 Estrutura do Formulário
```javascript
<div className='div_login_intermediaria'>
    <h1 className="h1_login">Cadastre-se</h1>
```
- O título "Cadastre-se" é exibido na tela.

```javascript
<form className='form_login' onSubmit={handleSubmit}>
```
- O formulário é criado e vinculado à função `handleSubmit`.

### 6.2 Campos do Formulário
```javascript
<label>
    <span>Nome</span>
    <input type="text" value={nome} name="nome" onChange={(e) => setNome(e.target.value)} placeholder="Digite o seu nome" maxLength={10} autoComplete='off'/>
</label>
```
- O campo de nome possui um `maxLength` de 10 caracteres.
- `onChange` atualiza o estado `nome` conforme o usuário digita.

```javascript
<label>
    <span>E-mail</span>
    <input type="email" value={email} name="email" onChange={(e) => setEmail(e.target.value)} placeholder="Digite o seu email" />
</label>
```
- Campo de e-mail com tipo `email` para validação automática.

```javascript
<label>
    <span>Senha</span>
    <input type="password" value={senha} name="senha" onChange={(e) => setSenha(e.target.value)} placeholder="Digite a sua senha" />
</label>
```
- Campo de senha com tipo `password`.

### 6.3 Botão de Cadastro
```javascript
<input type="submit" value='Cadastre-se' className='btn_login btn_font' />
```
- Botão que envia o formulário.

### 6.4 Exibição de Erros
```javascript
{errorMessage && <p className="error-message">{errorMessage}</p>}
```
- Se houver uma mensagem de erro, ela é exibida.

### 6.5 Link para Login
```javascript
<div className='btn_texto'> <p>Já possui conta ? <Link to="/" className='btn_cadastro'>Faça o login</Link></p>
</div>
```
- Link para a tela de login caso o usuário já tenha uma conta.

## 7. Exportação do Componente
```javascript
export default Cadastro;
```
- O componente `Cadastro` é exportado para ser utilizado em outras partes do projeto.


# `Login.jsx`

#### 1. **Importações**
```javascript
import './Login.css'
import { Link } from 'react-router-dom';
import { useFetch } from '../hooks/useFetch';
import { useState } from 'react';
import EsqueceuSenhaModal from "./EsqueceuSenhaModal";
import { useNavigate } from 'react-router-dom';
```
- Importa os estilos CSS do componente (`Login.css`).
- Importa `Link` e `useNavigate` do `react-router-dom` para navegação entre páginas.
- Importa `useFetch`, um hook personalizado para fazer requisições HTTP.
- Importa `useState` para gerenciamento de estado.
- Importa `EsqueceuSenhaModal`, um modal para recuperação de senha.

#### 2. **Definição da URL da API**
```javascript
const url = "http://localhost:8080/v1/usuarios/login";
```
- Define a URL da API para onde será enviada a requisição de login.

#### 3. **Estados do Componente**
```javascript
const [email, setEmail] = useState("");
const [senha, setSenha] = useState("");
const { httpConfig, loading, errorMessage } = useFetch();
const [mostrarModal, setMostrarModal] = useState(false);
const navigate = useNavigate();
```
- `email` e `senha`: armazenam os valores dos campos do formulário.
- `httpConfig`: função do `useFetch` para realizar requisições HTTP.
- `loading`: indica se a requisição está em andamento.
- `errorMessage`: armazena mensagens de erro da requisição.
- `mostrarModal`: controla a exibição do modal de "Esqueceu a senha?".
- `navigate`: função para redirecionamento de rotas.

#### 4. **Manipulação do Formulário**
```javascript
const handleSubmit = async (e) => {
    e.preventDefault();
    const loginRequestDTO = { email, senha };
    const response = await httpConfig(loginRequestDTO, "POST", url);
    setEmail("");
    setSenha("");
    
    if (response) {
        navigate(`/home/${response.idUsuario}`);
    }
};
```
- Previne o comportamento padrão do formulário (`e.preventDefault()`).
- Monta um objeto `loginRequestDTO` com `email` e `senha`.
- Chama `httpConfig` para enviar os dados para a API.
- Limpa os campos `email` e `senha` após a requisição.
- Se a resposta for bem-sucedida, redireciona o usuário para `/home/{idUsuario}`.

#### 5. **Estrutura JSX**
```javascript
return (
    <div className="login">
        <div className="container_login">
            <img src="/login_cadastro_img.jpg" alt="imagem ao lado do login" />
            <div className='div_login_intermediaria'>
                <h1 className="h1_login">Login</h1>
                <form className='form_login' onSubmit={handleSubmit}>
                    <label>
                        <span>E-mail</span>
                        <input type="email" value={email} name="email" placeholder="Digite o seu email" onChange={(e) => setEmail(e.target.value)} />
                    </label>
                    <label>
                        <span>Senha</span>
                        <input type="password" value={senha} name="senha" placeholder="Digite a sua senha" onChange={(e) => setSenha(e.target.value)} />
                    </label>
                    <div>
                        <button type="button" className='btn_esqueceu_senha' onClick={() => setMostrarModal(true)}>
                            Esqueceu a senha?
                        </button>
                    </div>
                    {loading && <input type="submit" disabled value='Aguarde' className='btn_login' />}
                    {!loading && <input type="submit" value='Login' className='btn_login' />}
                    {errorMessage && <p className="error-message">{errorMessage}</p>}
                    <div className='btn_texto'> <p>Ainda não Possui uma Conta ? <Link to="/cadastro" className='btn_cadastro'>Cadastre-se</Link></p>
                    </div>
                </form>
            </div>
        </div>
        {mostrarModal && <EsqueceuSenhaModal fechar={() => setMostrarModal(false)} />}
    </div>
);
```

#### 6. **Elementos Importantes**
- **Imagem:** Exibe uma imagem ao lado do login.
- **Campos de Entrada:** Inputs para email e senha, com `onChange` para atualizar os estados.
- **Botão "Esqueceu a senha?":** Abre o modal de recuperação de senha.
- **Botão de Login:**
  - Se `loading` for `true`, o botão fica desativado com a mensagem "Aguarde".
  - Caso contrário, exibe "Login".
- **Mensagens de erro:** Caso `errorMessage` esteja preenchida, é exibida ao usuário.
- **Link para Cadastro:** Se o usuário não tiver conta, pode se cadastrar.

#### 7. **Exibição do Modal**
```javascript
{mostrarModal && <EsqueceuSenhaModal fechar={() => setMostrarModal(false)} />}
```
- Se `mostrarModal` for `true`, o modal de recuperação de senha é exibido.
- O modal pode ser fechado ao chamar `setMostrarModal(false)`.

#### 8. **Exportação do Componente**
```javascript
export default Login;
```
- Permite que `Login.jsx` seja importado e utilizado em outras partes do projeto.
# Componente `EsqueceuSenhaModal`

### Visão Geral
O componente `EsqueceuSenhaModal` é um modal que permite aos usuários recuperarem sua senha por meio do envio de um novo valor para a API. Ele utiliza o hook `useFetch` para realizar a requisição de alteração da senha.

---

### Estrutura do Componente

1. **Importação de Dependências:**
   - `useFetch`: Hook personalizado para fazer requisições HTTP.
   - `useState`: Hook do React para gerenciar estado.
   - Arquivo de estilização `EsqueceuSenhaModal.css`.

2. **Propriedade `fechar`**:
   - Recebida via `props`, permite fechar o modal.

3. **Estados (`useState`)**:
   - `email`: Armazena o e-mail do usuário.
   - `senha`: Armazena a nova senha informada pelo usuário.
   - `mensagemSucesso`: Exibe uma mensagem de sucesso após uma requisição bem-sucedida.

4. **Hook `useFetch`**:
   - `httpConfig`: Função para realizar requisições HTTP.
   - `loading`: Indica se a requisição está em andamento.
   - `errorMessage`: Armazena mensagens de erro, caso ocorram.

5. **Funções**:
   - `handleOutsideClick(e)`: Fecha o modal ao clicar fora da área do formulário.
   - `handleSubmit(e)`: Envia a nova senha para a API via `PATCH`.

---

### Fluxo da Requisição HTTP
1. O usuário insere seu e-mail e a nova senha.
2. Ao enviar o formulário, a função `handleSubmit` é acionada.
3. A URL da requisição é gerada dinâmicamente com o e-mail do usuário.
4. A função `httpConfig` do hook `useFetch` faz uma requisição `PATCH` para atualizar a senha.
5. Caso a requisição seja bem-sucedida, a `mensagemSucesso` é definida com a resposta da API.
6. Se houver erro, a `errorMessage` exibe a mensagem correspondente.

---

### Renderização do Modal
O modal exibe:
- Campos para entrada de e-mail e senha.
- Botões:
  - **Enviar**: Desabilitado enquanto a requisição está em andamento.
  - **Fechar**: Fecha o modal chamando a função `fechar()`.
- Mensagens de erro ou sucesso conforme o resultado da requisição.

---

### Considerações Finais
- A URL para a requisição é construída dinamicamente usando `encodeURIComponent(email)` para evitar problemas com caracteres especiais.
- O modal fecha automaticamente quando o usuário clica fora da área principal.
- Mensagens de erro e sucesso melhoram a experiência do usuário ao fornecer feedback imediato.


# `ComponentMarcar`

Este componente é responsável por permitir que o usuário marque um evento, preenchendo detalhes como nome do evento, hora de início, hora de término e descrição. O evento é enviado a um servidor quando o formulário é enviado. Abaixo está a explicação detalhada de cada parte do código:

### 1. **Importações**
   - `useState`: Hook do React para criar variáveis de estado dentro do componente.
   - `useFetch`: Hook personalizado (presumivelmente) para fazer requisições HTTP.
   - `useParams`: Hook do `react-router-dom` para acessar os parâmetros da URL.
   - `useState` e `useFetch` são utilizados para gerenciar o estado dos campos do formulário e fazer a requisição de envio dos dados do evento.

### 2. **Estado Local**
   - `nomeEvento`, `horaInicioEvento`, `horaTerminoEvento`, e `descricaoEvento` são variáveis de estado que armazenam os valores dos campos do formulário.
   - `id`: Obtido da URL, representando o identificador do usuário.
   - `httpConfig`, `loading` e `errorMessage`: Provêm do hook `useFetch`, que gerencia a requisição HTTP. `httpConfig` é uma função para configurar e enviar a requisição, `loading` indica se a requisição está em andamento, e `errorMessage` armazena qualquer erro que ocorra durante a requisição.

### 3. **Função `handleSubmit`**
   - Esta função é acionada quando o formulário é enviado. Ela impede o comportamento padrão do formulário (com `e.preventDefault()`), cria um objeto `evento` com os dados do formulário, e faz uma requisição `POST` para o servidor utilizando a função `httpConfig`.
   - Caso a requisição seja bem-sucedida, os campos do formulário são limpos.

### 4. **Estrutura do JSX**
   - O componente retorna um layout de formulário que contém:
     - **Campos de Entrada**: 
       - Um campo de texto para o nome do evento.
       - Dois campos de hora para definir o início e o término do evento.
       - Uma área de texto para a descrição do evento.
     - **Botão de Envio**:
       - O botão de envio muda dependendo do estado `loading`. Caso o envio esteja em andamento, ele exibe "Aguarde", caso contrário, exibe "Salvar Evento".
     - **Mensagem de Erro**: Se ocorrer algum erro durante a requisição, a mensagem de erro será exibida.

### 5. **CSS**
   - O componente usa classes CSS importadas de um arquivo externo `Components_Styles.css` para estilizar o formulário e os elementos do componente.

### 6. **Fluxo de Dados**
   - O formulário é controlado pelo estado (`useState`). Os campos do formulário são atualizados à medida que o usuário interage, e ao enviar o formulário, os dados são enviados para o servidor.

### 7. **URL de Requisição**
   - O URL `http://localhost:8080/v1/eventos` é utilizado para enviar os dados do evento ao servidor, que provavelmente irá armazená-los ou processá-los de alguma forma.

### 8. **Erros e Carregamento**
   - Durante o processo de envio, se a requisição estiver carregando, o botão de envio exibirá "Aguarde". Caso ocorra um erro, a mensagem de erro será exibida abaixo do formulário.

#  `ComponentSemEvento`

Este componente exibe uma mensagem para o usuário caso ele ainda não tenha eventos marcados. Também fornece a opção de clicar em um ícone para marcar um novo evento. Abaixo está a explicação detalhada de cada parte do código:

### 1. **Importações**
   - `import "./Components_Styles.css"`: O componente importa um arquivo CSS externo para aplicar estilos personalizados.

### 2. **Componente `ComponentSemEvento`**
   - Este componente recebe uma propriedade chamada `onMarcarEvento`, que é uma função que será executada quando o usuário clicar no ícone para marcar um evento.

### 3. **Estrutura do JSX**
   O componente retorna um bloco de HTML com a seguinte estrutura:
   - **Título**:
     - Um título `<h2>` com a classe `fontSessaoPrincipal`, que informa ao usuário que ele ainda não possui eventos marcados.
   - **Ícone de Marcação de Evento**:
     - Um ícone SVG representando um "mais" (`+`), indicando a ação de adicionar um evento. O ícone é um círculo com um símbolo de adição no centro. Ele está encapsulado dentro de um link (`<a>`), e ao ser clicado, chama a função `onMarcarEvento`.
   - **Mensagem de Ação**:
     - Outro título `<h2>` com a mesma classe `fontSessaoPrincipal`, que incentiva o usuário a clicar no ícone para marcar um evento.

### 4. **SVG**
   - O componente inclui um ícone SVG com um formato circular, representando a ação de adicionar algo. O `path` desenha o ícone de adição no centro da figura, e o preenchimento da cor é dado pelo código `fill="#159A9C"`, que dá uma tonalidade de azul-verde ao ícone.

### 5. **Fluxo de Dados**
   - A função `onMarcarEvento` é passada como propriedade para o componente e é executada quando o usuário clica no ícone. Esta função é responsável por iniciar o processo de marcação de um novo evento.

### 6. **CSS**
   - O componente usa classes CSS, como `sessaoPrincipal` e `fontSessaoPrincipal`, para estilizar os elementos. Estas classes são definidas no arquivo `Components_Styles.css`.

### 7. **Comportamento Interativo**
   - Quando o ícone é clicado, a função `onMarcarEvento` é chamada, permitindo ao usuário iniciar o processo de marcação de um evento.



# Hook Personalizado `useFetch`

O arquivo `useFetch.jsx` define um hook personalizado chamado `useFetch`, que encapsula a lógica de requisições HTTP assíncronas usando `fetch`. Ele gerencia estados de carregamento, resposta e erro. Abaixo está a explicação detalhada de seu funcionamento.

---

## 1. Importação de Hooks

```js
import { useState, useEffect } from "react";
```
- `useState` é usado para gerenciar estados locais como `data`, `loading` e `errorMessage`.
- `useEffect` poderia ser usado para efeitos colaterais, mas não é necessário no código atual.

---

## 2. Definição do Hook `useFetch`

```js
export const useFetch = () => {
```
- O hook é exportado como uma função para ser reutilizável em vários componentes.

### 2.1 Estados

```js
const [data, setData] = useState(null);
const [loading, setLoading] = useState(false);
const [errorMessage, setErrorMessage] = useState("");
```
- `data`: Armazena os dados da resposta.
- `loading`: Indica se a requisição está em andamento.
- `errorMessage`: Armazena mensagens de erro caso ocorra um problema.

---

## 3. Função `httpConfig`

A função `httpConfig` é responsável por fazer requisições HTTP (GET, POST, PUT, PATCH e DELETE).

```js
const httpConfig = async (requestData, method, url) => {
```
- `requestData`: Dados enviados na requisição (para métodos como POST e PUT).
- `method`: Método HTTP (`GET`, `POST`, `PUT`, etc.).
- `url`: URL para a qual a requisição será feita.

### 3.1 Configuração da Requisição

```js
setLoading(true);
setErrorMessage("");
```
- Indica que a requisição começou e limpa mensagens de erro anteriores.

```js
const options = {
    method,
    headers: {
        "Content-Type": "application/json"
    },
    body: method === "POST" || method === "PATCH" || method === "DELETE" || method === "PUT" ? JSON.stringify(requestData) : null
};
```
- Configura os headers e transforma os dados para JSON, se necessário.

### 3.2 Execução da Requisição e Tratamento de Erros

```js
try {
    const res = await fetch(url, options);
```
- Faz a requisição assíncrona.

```js
if (!res.ok) {
    let errorData;
    const contentType = res.headers.get("content-type");
```
- Se a resposta não for bem-sucedida (`res.ok === false`), tenta extrair a mensagem de erro.
- Verifica se a resposta é JSON ou texto.

```js
throw {
    message: errorData.mensagem,
    status: res.status,
};
```
- Lança um erro com a mensagem e status HTTP.

### 3.3 Tratamento da Resposta

```js
const contentType = res.headers.get("content-type");
if (contentType && contentType.includes("application/json")) {
    const responseData = await res.json();
    setData(responseData);
    return responseData;
} else {
    const textData = "Alteração bem sucedida";
    setData(textData);
    return textData;
}
```
- Se o conteúdo for JSON, armazena os dados na variável `data`.
- Caso contrário, define `data` como "Alteração bem sucedida" (útil para respostas sem corpo).

### 3.4 Tratamento de Erros

```js
} catch (error) {
    if (typeof error === 'object' && error !== null && error.message) {
        setErrorMessage(error.message);
    } else {
        setErrorMessage("Erro desconhecido.");
    }
    return null;
} finally {
    setLoading(false);
}
```
- Captura erros, define mensagens adequadas e garante que o estado `loading` seja atualizado.

---

## 4. Função `fetchData`

```js
const fetchData = async (url) => {
```
- Usada para requisições `GET`, sem necessidade de enviar corpo na requisição.

```js
setLoading(true);
setErrorMessage("");
```
- Define `loading` como `true` e limpa mensagens de erro anteriores.

```js
const res = await fetch(url);
const json = await res.json();
setData(json);
setLoading(false);
```
- Obtém os dados e armazena na variável `data`.

---

## 5. Retorno do Hook

```js
return { data, httpConfig, fetchData, loading, errorMessage };
```
- `data`: Armazena os dados retornados.
- `httpConfig`: Função para requisições personalizadas (POST, PUT, DELETE, etc.).
- `fetchData`: Método para requisições `GET`.
- `loading`: Estado de carregamento.
- `errorMessage`: Mensagens de erro.

---

## 6. Uso do Hook `useFetch`

```js
const { data, httpConfig, fetchData, loading, errorMessage } = useFetch();
```





