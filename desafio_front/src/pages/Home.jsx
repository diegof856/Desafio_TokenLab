import { Link, useParams } from 'react-router-dom';
import './Home.css'
import ComponentSemEventos from '../components/ComponentSemEventos';
import ComponentMarcar from '../components/ComponentMarcar';
import ComponentEventos from '../components/ComponentEventos';
import { useFetch } from '../hooks/useFetch';
import { useEffect, useState } from "react";



const Home = () => {
    const [nomeUsuario, setNomeUsuario] = useState();
    const { id } = useParams();
    const url = `http://localhost:8080/v1/usuarios/${id}`;
    const { data, fetchData, httpConfig } = useFetch();

    const [mostrarComponentMarcar, setMostrarComponentMarcar] = useState(false);

    useEffect(() => {
        if (id) {
            fetchData(url);
        }
    }, [id]);
    useEffect(() => {
        if (data) {
            setNomeUsuario(data.nome);
        }

    }, [data]);

    const handleDeletarConta = async () => {

        const response = httpConfig(null, "DELETE", url)
        if (response) {
            alert("Conta deletada com sucesso")
        }


    };
    const handleMarcarEvento = () => {

        if (mostrarComponentMarcar) {
            setMostrarComponentMarcar(false)
        } else {
            setMostrarComponentMarcar(true);
        }

    }
    let componenteEventos;
    if (data && data.eventos) {
        if (data.eventos.length === 0) {
            componenteEventos = <ComponentSemEventos onMarcarEvento={handleMarcarEvento} />;
        } else {
            componenteEventos = <ComponentEventos eventos={data.eventos} />;
        }
    }

    return (
        <div className="container_Home">
            <nav className="mainNav">
                <h2 className='h2_menu'>Olá {nomeUsuario}</h2>
                <h2 className='h2_menu'>Bem Vindo!</h2>
                <div className="menus">
                    <ul className="menu">
                        <li><a onClick={handleMarcarEvento} className="btn_marcar">Marcar Eventos</a></li>

                    </ul>
                    <ul className='menu btn_del_sair'>
                        <li>
                            <Link to="/">
                                <svg width="20" height="20" viewBox="0 0 20 20" fill="none" xmlns="http://www.w3.org/2000/svg">
                                    <path d="M3 0.5H9C9.79565 0.5 10.5587 0.816071 11.1213 1.37868C11.6839 1.94129 12 2.70435 12 3.5V7.5H11V3.5C11 2.96957 10.7893 2.46086 10.4142 2.08579C10.0391 1.71071 9.53043 1.5 9 1.5H3C2.46957 1.5 1.96086 1.71071 1.58579 2.08579C1.21071 2.46086 1 2.96957 1 3.5V16.5C1 17.0304 1.21071 17.5391 1.58579 17.9142C1.96086 18.2893 2.46957 18.5 3 18.5H9C9.53043 18.5 10.0391 18.2893 10.4142 17.9142C10.7893 17.5391 11 17.0304 11 16.5V12.5H12V16.5C12 17.2956 11.6839 18.0587 11.1213 18.6213C10.5587 19.1839 9.79565 19.5 9 19.5H3C2.20435 19.5 1.44129 19.1839 0.87868 18.6213C0.316071 18.0587 0 17.2956 0 16.5V3.5C0 2.70435 0.316071 1.94129 0.87868 1.37868C1.44129 0.816071 2.20435 0.5 3 0.5ZM6 9.5H17.25L14 6.25L14.66 5.5L19.16 10L14.66 14.5L14 13.75L17.25 10.5H6V9.5Z" fill="white" />
                                </svg>

                                Sair</Link>
                        </li>
                        <li><Link to="/" onClick={handleDeletarConta}>Deletar Conta</Link></li>
                    </ul>
                </div>
            </nav>
            <main className={data && data.eventos && data.eventos.length > 0 ? "main-com-eventos" : "main"}>
                {!mostrarComponentMarcar && componenteEventos}
                {mostrarComponentMarcar && <ComponentMarcar />}

            </main>
        </div>
    )
}
export default Home;