import { useState } from "react"
import { useFetch } from '../hooks/useFetch';
import {  useParams } from 'react-router-dom';
import "./Components_Styles.css"
const ComponentMarcar = () =>{
    const [nomeEvento,setNomeEvento] = useState("");
    const [horaInicioEvento,setHoraInicioEvento] = useState("");
    const [horaTerminoEvento,setHoraTerminoEvento] = useState("");
    const [descricaoEvento,setDescricaoEvento] = useState("");
    const { id } = useParams();
    const url = `http://localhost:8080/v1/eventos`;
    const { httpConfig, loading, errorMessage } = useFetch()
   
    const handleSubmit = async (e) =>{
        e.preventDefault();
        
        const evento = {
            idUsuario: id,
            nomeEvento,
            horaInicio: horaInicioEvento,
            horaTermino: horaTerminoEvento,
            descricao: descricaoEvento
        }
        const res = httpConfig(evento, "POST", url)
        if(res){
            setNomeEvento("")
            setDescricaoEvento("")
            setHoraInicioEvento("")
            setHoraTerminoEvento("")
           
        }
    }
return(

   
    <div className="container_marcar">
        <section>
           <div className="titulo_div">
           <h2 className="h2_marcar">Escolha Data e Hora do Evento</h2>
           <span className="linha"></span>
            </div> 
            <form className="form_marcar" onSubmit={handleSubmit}>

            <label>
                <span>Digite o nome do evento</span>
                <input type="text" value={nomeEvento} name="Digite o nome do evento" autoComplete="off" onChange={(e) => setNomeEvento(e.target.value)}/>
            </label>
            <label>
                <span>Hora do inicio do evento</span>
                <input type="time" value={horaInicioEvento} name="Hora do inicio do evento"  onChange={(e) => setHoraInicioEvento(e.target.value)}/>
            </label>
            <label>
                <span>Hora do Termino do evento</span>
                <input type="time" value={horaTerminoEvento} name="Hora do termino do evento"  onChange={(e) => setHoraTerminoEvento(e.target.value)}/>
            </label>
            <label>
                <span>Faça uma breve descrição do que é o evento</span>
                <textarea rows={4} cols={50} value={descricaoEvento} onChange={(e) => setDescricaoEvento(e.target.value)}></textarea>
            </label>
           
            {loading &&  <input  className = "btn"type="submit" value="Aguarde"/>}
            {!loading &&  <input  className = "btn"type="submit" value="Salvar Evento"/>}
            {errorMessage && <p className="erro_mensagem">{errorMessage}</p>}
            </form>

        </section>

    </div>
)
}

export default ComponentMarcar;