import "./App.css"
import {BrowserRouter, Routes, Route} from 'react-router-dom'
import Login from "./pages/Login.jsx"
import Cadastro from "./pages/Cadastro.jsx"
import Home from "./pages/Home.jsx"

function App() {


  return (
    <div >
     
      <BrowserRouter>
      <Routes>
        <Route path="/" element={<Login/>}/>
        <Route path="/cadastro" element={<Cadastro />} />
        <Route path="/home/:id" element = {<Home/>}/>
       
      </Routes>
      </BrowserRouter>
    </div>
  )
}

export default App
