import { useState } from "react"
import axios from 'axios'
import {Link,useNavigate} from 'react-router-dom'
function Login() {
    const nav=useNavigate() 
    const [message,setMessage]=useState("");
    const [form,setform]=useState({
        email:"",
        password:""
    })
    const change = (e) =>{
        setform({...form,[e.target.name]:e.target.value})
    }
    const submit= async (e) =>{
        e.preventDefault();
        try{
            const result= await axios.post("http://localhost:8081/api/user/login",form,{withCredentials:true})
            console.log(result)
            console.log(result.data)
            // alert(result.data)
            setMessage(result.data)
            setTimeout(() => nav("/mid"), 2000);
            // nav("/mid")
        }
        catch(err){
            console.log(err)
            setMessage(err.response.data)

        }
        }
    return (
        <>
        <div className="page-section">
            <div className="home-card">
            <h2>🔐 Login</h2>
            <div className="home-container">
        <form onSubmit={submit}>
            {/* <p>email:{form.email}</p>
            <p>password:{form.password}</p> */}
            {message && (<p style={{ color: message.toLowerCase().includes("success") ? "green" : "red" }}>
                    {message}</p>)}
            {/* <label>email</label> */}
            <input onChange={change}placeholder="📧 email" type="email" name="email"/>   
            {/* <label>password</label>          */}
            <input onChange={change}placeholder="🔒 password" type="password" name="password"/>
            <button type="submit">login</button><br/>
             <div className="link-row">
                <a href="/reg">Sign up</a>
                <a href="/forgetpass">Forgot Password</a>
      </div>
       </form>
    </div>
    </div>
    </div>
        </>
    )
}
export default Login


