import { useFetch } from '../hooks/useFetch';
const ComponentDeletarEvento = ({ id, fechar }) => {
    const { httpConfig, loading, errorMessage } = useFetch()
    const url = `http://localhost:8080/v1/eventos/${id}`
    const handleOutsideClick = (e) => {

        if (e.target.classList.contains('modal_overlay')) {
            fechar();
        }

    };
    const handleCancelDeletar = (e) => {
        fechar();
    }
    const handleDeletarEvento = (e) => {
        const response = httpConfig(null,"DELETE",url);
        fechar();
    }
    return (

        <div className="modal_overlay" onClick={handleOutsideClick}>
            <article className="btns_deletar">
                <h2>Tem certeza?</h2>
                <div className="btns_deletar_alinhamento">
                    <button className="btn" onClick={handleDeletarEvento}>Sim</button>
                    <button className="btn_deletar_cancelar" onClick={handleCancelDeletar}>Não</button>
                </div>

            </article>
        </div>
    )
}
export default ComponentDeletarEvento;