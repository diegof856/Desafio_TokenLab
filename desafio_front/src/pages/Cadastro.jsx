import { Link, useNavigate } from 'react-router-dom';
import "./Login.css";
import "./Cadastro.css";
import { useState } from 'react';

const url = "http://localhost:8080/v1/usuarios";
const Cadastro = () => {
    const [nome, setNome] = useState("");
    const [email, setEmail] = useState("");
    const [senha, setSenha] = useState("");
    const [errorMessage, setErrorMessage] = useState("");
    const navigate = useNavigate();

    const handleSubmit = async (e) => {
        e.preventDefault();
        setErrorMessage("");
        try {
            const usuarioRequestDTO = {
                nome,
                email,
                senha,
            };

            const res = await fetch(url, {
                method: "POST",
                headers: {
                    "Content-Type": "application/json",
                },
                body: JSON.stringify(usuarioRequestDTO),
            });

            if (!res.ok) {
                const errorData = await res.json();
                setNome("")
                setEmail("")
                setSenha("")
                throw new Error(errorData.mensagem);
            }
            setNome("")
            setEmail("")
            setSenha("")
            navigate("/");
        } catch (error) {
            setErrorMessage(error.message);
        }
    };

    return (
        <div className="login">
            <img src="/login_cadastro_img.jpg" alt="imagem ao lado do login" />
            <div className="container_login">
                <div className='div_login_intermediaria'>
                    <h1 className="h1_login">Cadastre-se</h1>

                    <form className='form_login' onSubmit={handleSubmit}>
                        <label >
                            <span>Nome</span>
                            <input type="text" value={nome} name="nome" onChange={(e) => setNome(e.target.value)} placeholder="Digite o seu nome" maxLength={30}/>
                        </label>
                        <label >
                            <span>E-mail</span>
                            <input type="email" value={email} name="email" onChange={(e) => setEmail(e.target.value)} placeholder="Digite o seu email" />
                        </label>
                        <label >
                            <span>Senha</span>
                            <input type="password" value={senha} name="senha" onChange={(e) => setSenha(e.target.value)} placeholder="Digite a sua senha" />
                        </label>
                        <input type="submit" value='Cadastre-se' className='btn_login btn_font' />
                        {errorMessage && <p className="error-message">{errorMessage}</p>}
                        <div className='btn_texto'> <p>Já possui conta ? <Link to="/" className='btn_cadastro'>Faça o login</Link></p>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    );
};

export default Cadastro;