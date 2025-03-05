import './Login.css'
import { Link } from 'react-router-dom';
import { useFetch } from '../hooks/useFetch';
import { useState } from 'react';
const url = "http://localhost:8080/v1/usuarios/login"
const Login = () => {
    const[email, setEmail] = useState("")
    const[senha, setSenha] = useState("")
    const {httpConfig, loading } = useFetch(url)
    
    const handleSubmit = async (e) =>{
        e.preventDefault();
        const loginRequestDTO = {
            email,
            senha
            
        } 
        const response = await httpConfig(loginRequestDTO, "POST");

        setEmail("");
        setSenha("");
        if (response) {
         
            alert("Login bem-sucedido: " + response.nome+", infelizmente não deu tempo de implementar o restante");
            
        }
    }
    
    return (

        <div className="login">

            <img src="/login_cadastro_img.jpg" alt="imagem ao lado do login" />
            <div className="container_login">
                <div className='div_login_intermediaria'>
                    <h1 className="h1_login">Login</h1>

                    <form className='form_login' onSubmit={handleSubmit}>

                        <label >
                            <span>E-mail</span>
                            <input type="email" value={email} name="email" placeholder="Digite o seu email" onChange={(e) => setEmail(e.target.value)}/>
                        </label>
                        <label >
                            <span>Senha</span>
                            <input type="password" value={senha} name="senha"  placeholder="Digite a sua senha" onChange={(e) => setSenha(e.target.value)}/>
                        </label>
                        <div><a href="" className='btn_esqueceu_senha'>Esqueceu a senha</a></div>
                        {loading && <input type="submit" disabled value='Aguarde' className='btn_login' />}
                        {!loading && <input type="submit" value='Login' className='btn_login' />}
                        <div className='btn_texto'> <p>Ainda não Possui uma Conta ? <Link to="/v1/usuarios" className='btn_cadastro'>Cadastre-se</Link></p>
                        </div>
                    </form>

                </div>
            </div>
        </div>

    )
}
export default Login;