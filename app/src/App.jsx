// import './App.css';
import { BrowserRouter, Routes, Route } from 'react-router-dom';
import './components/style.css'

import Home from './components/Home';
import Register from './components/Register';
import Login from './components/Login';
import Addexpense from './components/Addexpense';
import Myexpenses from './components/Myexpenses';
import Update from './components/Update';
import Mybudget from './components/Mybudget';
import ForgetPass from './components/ForgetPass';
import Profile from './components/Profile';
import Middle from './components/Middle';
import Updpro from './components/Updpro';
import BudgetAdd from './components/BudgetAdd';
import About from './components/About';

function App() {
    return (
        <>
            <BrowserRouter>
                <Routes>
                    <Route path="/" element={<Home />} />
                    <Route path="/reg" element={<Register />} />
                    <Route path="/log" element={<Login />} />
                    <Route path="/addexp" element={<Addexpense />} />
                    <Route path="/myexp" element={<Myexpenses />} />
                    <Route path="/upd" element={<Update />} />
                    <Route path="/addbud" element={<BudgetAdd/>} />
                    <Route path="/mybud" element={<Mybudget />} />
                    <Route path="/forgetpass" element={<ForgetPass/>}/>
                    <Route path="profile" element={<Profile/>}/>
                    <Route path="/mid" element={<Middle/>}/>
                    <Route path="/upro" element={<Updpro/>}/>
                    <Route path='/about' element={<About/>}/>
                </Routes>
            </BrowserRouter>
        </>
    );
}

export default App;
