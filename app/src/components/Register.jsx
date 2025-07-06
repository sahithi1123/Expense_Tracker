import axios from 'axios'
import { useState } from 'react'
import {useNavigate } from "react-router-dom"
function Register() {
    const nav=useNavigate()
    const [form,setform]=useState({
        firstName:"",
        lastName:"",
        username:"",
        email:"",
        password:"",
        phoneNum:"",
        gender:"male"
    })
    const change = (e) =>{
        setform({...form,[e.target.name]:e.target.value})
    }
    const submit = async (e) =>{
        e.preventDefault();
        try{
            const result=await axios.post("http://localhost:8081/api/user/register",form)
            console.log(result)
            console.log(result.data)
            alert(result.data)
            nav('/')
        }
        catch(err){
            console.log(err)
            alert(err.response.data)
        }    
    }
    return (
        <>
        <div className="page-section">
            <div className="home-card">
            <h2>📝 Register</h2>
            <div className="home-container">
        {/* <p>username:{form.username}</p>
         <p>email:{form.email}</p>
        <p>password:{form.password}</p> */}
        <form onSubmit={submit}>
            {/* <label>FirstName</label> */}
            <input onChange={change}placeholder=" First name" type="text" name="firstName"/>
            {/* <label>LastName</label> */}
            <input onChange={change}placeholder=" Last name" type="text" name="lastName"/>
            {/* <label>username</label> */}
            <input onChange={change}placeholder=" Create username" type="text" name="username"/>
            {/* <label>email</label> */}
            <input onChange={change}placeholder="📧 Enter email" type="email" name="email"/>
            {/* <label>password</label> */}
            <input onChange={change}placeholder="🔒 Create password" type="password" name="password"/>
            {/* <label>PhoneNumber</label> */}
            <input onChange={change}placeholder="Enter phonenumber" type="tel" name="phoneNum"/>
            {/* <label>Gender</label> */}
            <select name="gender" onChange={change} required>
                <option value="male">male</option>
                <option value="female">female</option>
                <option value="others">others</option>
            </select><br/>
            <button type="submit">register</button>
        </form>
        </div>
        </div>
        </div>
        </>
    )
}

export default Register
 



