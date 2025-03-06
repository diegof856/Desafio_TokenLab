import './Login.css'
import { Link } from 'react-router-dom';
import { useFetch } from '../hooks/useFetch';
import { useState } from 'react';
import EsqueceuSenhaModal from "./EsqueceuSenhaModal";
import { useNavigate } from 'react-router-dom';
const url = "http://localhost:8080/v1/usuarios/login"
const Login = () => {
    const [email, setEmail] = useState("")
    const [senha, setSenha] = useState("")
    const { httpConfig, loading, errorMessage } = useFetch()
    const [mostrarModal, setMostrarModal] = useState(false);
    const navigate = useNavigate();
    const handleSubmit = async (e) => {
        e.preventDefault();
        const loginRequestDTO = {
            email,
            senha

        }

        const response = await httpConfig(loginRequestDTO, "POST",url);
        setEmail("");
        setSenha("");


        if (response) {
           
            navigate(`/${response.idUsuario}/home`)
           

        }


    }

    return (

        <div className={`login ${mostrarModal ? "blur-background" : ""}`}>

            <img src="/login_cadastro_img.jpg" alt="imagem ao lado do login" />
            <div className="container_login">
                <div className='div_login_intermediaria'>
                    <h1 className="h1_login">Login</h1>

                    <form className='form_login' onSubmit={handleSubmit}>

                        <label >
                            <span>E-mail</span>
                            <input type="email" value={email} name="email" placeholder="Digite o seu email" onChange={(e) => setEmail(e.target.value)} />
                        </label>
                        <label >
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

    )
}
export default Login;