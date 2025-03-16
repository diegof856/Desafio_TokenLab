import "./Components_Styles.css"
import ComponentEditarEvento from "./ComponentEditarEvento";
import ComponentDeletarEvento from "./ComponentDeletarEvento";
import { useState} from 'react';
const ComponentEventos = ({ eventos }) => {
    const [modalEditar,setModalEditar] = useState(false);
    const [modalDeletar, setModalDeletar] = useState(false);
   
    return (
        <section className="container_Eventos">
            {eventos &&
                eventos.map((evento) => (<ul key={evento.idEvento}>

                    <li ><svg xmlns="http://www.w3.org/2000/svg" width="23" height="23" viewBox="0 0 23 23" fill="none">
                        <path d="M18.5 8.5L19.75 5.75L22.5 4.5L19.75 3.25L18.5 0.5L17.25 3.25L14.5 4.5L17.25 5.75L18.5 8.5ZM11 9L8.5 3.5L6 9L0.5 11.5L6 14L8.5 19.5L11 14L16.5 11.5L11 9ZM18.5 14.5L17.25 17.25L14.5 18.5L17.25 19.75L18.5 22.5L19.75 19.75L22.5 18.5L19.75 17.25L18.5 14.5Z" fill="#DCEB10" />
                    </svg> {evento.nomeEvento}</li>
                    <li>
                        <svg xmlns="http://www.w3.org/2000/svg" width="12" height="13" viewBox="0 0 12 13" fill="none">
                            <g clip-path="url(#clip0_18_111)">
                                <path d="M3.75184 10.3939L3.00266 11.7457" stroke="black" stroke-linecap="round" stroke-linejoin="round" />
                                <path d="M8.24814 10.3939L8.99732 11.7457" stroke="black" stroke-linecap="round" stroke-linejoin="round" />
                                <path d="M5.99998 10.9963C8.48306 10.9963 10.496 8.98337 10.496 6.50029C10.496 4.01721 8.48306 2.00427 5.99998 2.00427C3.5169 2.00427 1.50397 4.01721 1.50397 6.50029C1.50397 8.98337 3.5169 10.9963 5.99998 10.9963Z" stroke="black" stroke-linecap="round" stroke-linejoin="round" />
                                <path d="M1.65442 5.30181C1.30925 5.04311 1.04581 4.69055 0.89552 4.28622C0.745232 3.88189 0.714448 3.44285 0.806842 3.0215C0.899235 2.60016 1.1109 2.21429 1.41659 1.90994C1.72228 1.60559 2.10907 1.39562 2.53082 1.30508C2.95257 1.21454 3.39147 1.24725 3.79513 1.39932C4.1988 1.55138 4.5502 1.81637 4.80739 2.16267" stroke="black" stroke-linecap="round" stroke-linejoin="round" />
                                <path d="M5.99997 2.00434L5.99999 1.255" stroke="black" stroke-linecap="round" stroke-linejoin="round" />
                                <path d="M6 4.25232V6.50032" stroke="black" stroke-linecap="round" stroke-linejoin="round" />
                                <path d="M6 6.50037L7.58958 8.08994" stroke="black" stroke-linecap="round" stroke-linejoin="round" />
                                <path d="M10.3456 5.30181C10.6908 5.04311 10.9542 4.69055 11.1045 4.28622C11.2548 3.88189 11.2856 3.44285 11.1932 3.0215C11.1008 2.60016 10.8891 2.21429 10.5834 1.90994C10.2777 1.60559 9.89094 1.39562 9.46919 1.30508C9.04744 1.21454 8.60855 1.24726 8.20488 1.39932C7.80121 1.55138 7.44982 1.81637 7.19263 2.16267" stroke="black" stroke-linecap="round" stroke-linejoin="round" />
                            </g>
                            <defs>
                                <clipPath id="clip0_18_111">
                                    <rect width="12" height="12" fill="white" transform="translate(0 0.5)" />
                                </clipPath>
                            </defs>
                        </svg> Hora de Início: {evento.horaInicio}
                    </li>
                    <li ><svg xmlns="http://www.w3.org/2000/svg" width="13" height="13" viewBox="0 0 13 13" fill="none">
                        <g clip-path="url(#clip0_18_97)">
                            <path d="M9.25437 6.5C11.0029 5.39094 12.1875 3.25975 12.1875 0.8125C12.1875 0.537333 12.1729 0.2665 12.1436 0H0.857179C0.827486 0.269809 0.812838 0.541062 0.813304 0.8125C0.813304 3.25975 1.99712 5.39094 3.74562 6.5C1.99712 7.60906 0.813304 9.74025 0.813304 12.1875C0.813304 12.4627 0.827929 12.7335 0.857179 13H12.1436C12.1729 12.7335 12.1875 12.4627 12.1875 12.1875C12.1875 9.74025 11.0029 7.60906 9.25437 6.5ZM2.03124 12.1875C2.03124 9.81419 3.0493 7.80244 4.87499 7.13863V5.86138C3.0493 5.19756 2.03124 3.185 2.03124 0.8125H10.9687C10.9687 3.18581 9.95068 5.19756 8.12499 5.86138V7.13863C9.95068 7.80244 10.9687 9.815 10.9687 12.1875H2.03124ZM7.86662 8.50037C6.95662 7.98444 6.90705 7.31494 6.90624 6.90869V6.09294C6.90624 5.68669 6.95418 5.01475 7.86824 4.498C8.35737 4.21444 8.77987 3.783 9.09999 3.24919H3.89999C4.22093 3.783 4.64424 4.21444 5.13337 4.49881C6.04337 5.01475 6.09293 5.68425 6.09374 6.0905V6.90625C6.09374 7.3125 6.0458 7.98444 5.13174 8.50119C4.20955 9.03662 3.52299 10.0969 3.31499 11.375H9.68418C9.47618 10.0961 8.7888 9.03581 7.8658 8.50037H7.86662Z" fill="black" />
                        </g>
                        <defs>
                            <clipPath id="clip0_18_97">
                                <rect width="13" height="13" fill="white" />
                            </clipPath>
                        </defs>
                    </svg>Hora de Término: {evento.horaTermino}</li>
                    <li><svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" viewBox="0 0 16 16" fill="none">
                        <path d="M9.25 2.375V4.875C9.25 5.04076 9.31585 5.19973 9.43306 5.31694C9.55027 5.43415 9.70924 5.5 9.875 5.5H12.375" stroke="black" strokeWidth="2" stroke-linecap="round" stroke-linejoin="round" />
                        <path d="M6.125 11.125H9.875M6.125 8.625H9.875M11.125 13.625H4.875C4.54348 13.625 4.22554 13.4933 3.99112 13.2589C3.7567 13.0245 3.625 12.7065 3.625 12.375V3.625C3.625 3.29348 3.7567 2.97554 3.99112 2.74112C4.22554 2.5067 4.54348 2.375 4.875 2.375H9.25L12.375 5.5V12.375C12.375 12.7065 12.2433 13.0245 12.0089 13.2589C11.7745 13.4933 11.4565 13.625 11.125 13.625Z" stroke="black" strokeWidth="2" stroke-linecap="round" stroke-linejoin="round" />
                    </svg>Descrição: {evento.descricao}</li>
                    <div className="btns_edit_cancel">
                    <button className="btn_ev btn_edit" onClick={() => setModalEditar(evento.idEvento)}><svg xmlns="http://www.w3.org/2000/svg" width="16" height="17" viewBox="0 0 16 17" fill="none">
                        <path d="M9.37333 6.5L10 7.12667L3.94667 13.1667H3.33333V12.5533L9.37333 6.5ZM11.7733 2.5C11.6067 2.5 11.4333 2.56667 11.3067 2.69333L10.0867 3.91333L12.5867 6.41333L13.8067 5.19333C14.0667 4.93333 14.0667 4.5 13.8067 4.25333L12.2467 2.69333C12.1133 2.56 11.9467 2.5 11.7733 2.5ZM9.37333 4.62667L2 12V14.5H4.5L11.8733 7.12667L9.37333 4.62667Z" fill="black" />
                    </svg>Editar</button>
                    <button className="btn_ev btn_cancel" onClick={() => setModalDeletar(evento.idEvento)}><svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" viewBox="0 0 16 16" fill="none">
                        <path d="M8.71094 8L14.1016 13.3984L13.3984 14.1016L8 8.71094L2.60156 14.1016L1.89844 13.3984L7.28906 8L1.89844 2.60156L2.60156 1.89844L8 7.28906L13.3984 1.89844L14.1016 2.60156L8.71094 8Z" fill="black" />
                    </svg>Cancelar</button>
                </div>
            </ul>))}
            {modalEditar && <ComponentEditarEvento id={modalEditar} fechar={() => setModalEditar(null)}/>}
                    {modalDeletar && <ComponentDeletarEvento id={modalDeletar} fechar={() => setModalDeletar(null)}/>}
                    </section >
    )
}
export default ComponentEventos;
