import "./App.css"
import {BrowserRouter, Routes, Route} from 'react-router-dom'
import Login from "./pages/Login.jsx"
import Cadastro from "./pages/Cadastro.jsx"
function App() {


  return (
    <div>
     
      <BrowserRouter>
      <Routes>
        <Route path="/v1/usuarios/login" element={<Login/>}/>
        <Route path="/v1/usuarios" element={<Cadastro />} />
      </Routes>
      </BrowserRouter>
    </div>
  )
}

export default App
