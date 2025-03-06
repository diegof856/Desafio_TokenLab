import "./EsqueceuSenhaModal.css"
import { useFetch } from '../hooks/useFetch';
import { useState} from 'react';
const EsqueceuSenhaModal = ({ fechar }) => {
    const [email, setEmail] = useState("")
    const [senha, setSenha] = useState("")
    const { httpConfig, loading, errorMessage} = useFetch(); 
    const [mensagemSucesso, setMensagemSucesso] = useState("");
    const handleOutsideClick = (e) => {
        if (e.target.classList.contains("modal-overlay")) {
            fechar();
        }
    };
    const handleSubmit = async (e) => {

        e.preventDefault();
        setMensagemSucesso("");
      
        const url = `http://localhost:8080/v1/usuarios/${encodeURIComponent(email)}`;
        
        const response = await httpConfig({senha:senha}, "PATCH", url);
       
           setMensagemSucesso(response) 
    };
    return (
        <div className="modal-overlay" onClick={handleOutsideClick}>
            <form className="modal" onSubmit={handleSubmit}>
                <h2>Recuperar Senha</h2>
                <input type="email" placeholder="Digite seu e-mail" onChange={(e) => setEmail(e.target.value)} />
                <input type="password" placeholder="Digite sua nova senha" onChange={(e) => setSenha(e.target.value)} />
                {loading && <button className="btn_enviar" disabled={true}>Aguarde</button>}
                {!loading && <button className="btn_enviar" type="submit">Enviar</button>}
                <button className="btn_fechar" onClick={fechar}>Fechar</button>
                {errorMessage && <p className="error-message">{errorMessage}</p>}
                {mensagemSucesso && <p className="mensagem-sucesso">{mensagemSucesso}</p>}

            </form>
        </div>
    )
}

export default EsqueceuSenhaModal;