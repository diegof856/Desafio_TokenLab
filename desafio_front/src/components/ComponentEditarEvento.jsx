import "./Components_Styles.css"
import { useState } from 'react';
import { useFetch } from '../hooks/useFetch';

const ComponentEditarEvento = ({ id, fechar }) => {

    const [novoNomeEvento, setNovoNomeEvento] = useState("");
    const [novaHoraInicioEvento, setNovaHoraInicioEvento] = useState("");
    const [novaHoraTerminoEvento, setNovaHoraTerminoEvento] = useState("");
    const [novaDescricaoEvento, setNovaDescricaoEvento] = useState("");
    const { httpConfig, loading, errorMessage } = useFetch()
   
    const url = `http://localhost:8080/v1/eventos/${id}`
    const handleOutsideClick = (e) => {

        if (e.target.classList.contains('modal_overlay')) {
            fechar();
        }

    };
    const handleSubmitEdit = (e) => {
        e.preventDefault();
        const atualizarEvento = {
            nomeEvento: novoNomeEvento,
            horaInicio: novaHoraInicioEvento,
            horaTermino: novaHoraTerminoEvento,
            descricao: novaDescricaoEvento,
        }
       
        const response = httpConfig(atualizarEvento, "PUT", url) 
        if(response){
            setNovaDescricaoEvento("")
            setNovaHoraInicioEvento("")
            setNovaHoraTerminoEvento("")
            setNovoNomeEvento("")
           
        }   
       
    };
    return (

        <div className="modal_overlay" onClick={handleOutsideClick}>

            <form className="editar_evento_from" onSubmit={handleSubmitEdit} onClick={(e) => e.stopPropagation()}>

                <label>
                    <span>Digite o novo nome do evento</span>
                    <input type="text" value={novoNomeEvento} name="Digite o novo nome do evento" autoComplete="off" onChange={(e) => setNovoNomeEvento(e.target.value)} />
                </label>
                <label>
                    <span>Digite o novo horario de inicio do evento</span>
                    <input type="time" value={novaHoraInicioEvento} name="Digite o novo horario de inicio do evento" autoComplete="off" onChange={(e) => setNovaHoraInicioEvento(e.target.value)} />
                </label>
                <label>
                    <span>Digite o novo horario de termino do evento</span>
                    <input type="time" value={novaHoraTerminoEvento} name="Digite o novo horario de termino do evento" autoComplete="off" onChange={(e) => setNovaHoraTerminoEvento(e.target.value)} />
                </label>
                <label>
                    <span>Faça uma nova breve descrição do que é o evento</span>
                    <textarea rows={4} cols={50} value={novaDescricaoEvento} onChange={(e) => setNovaDescricaoEvento(e.target.value)}></textarea>
                </label>
                {loading &&  <input  className = "btn"type="submit" value="Aguarde"/>}
               {!loading &&  <input className="btn btn_color" type="submit" value="Salvar"/>}
               {errorMessage && <p className="erro_mensagem">{errorMessage}</p>}
              
            </form>
        </div>
    )
}
export default ComponentEditarEvento;