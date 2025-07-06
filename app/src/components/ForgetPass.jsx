import { useState } from "react"
import axios from "axios"
import { useNavigate } from 'react-router-dom'
function ForgetPass(){
    const nav=useNavigate()
    const [npsw,setnpsw]=useState("");
    const [cpsw,setcpsw]=useState("");
    const[form,setform]=useState(
            {
                email:""
            }
        )
        const change = (e) =>
        {
          setform({...form,[e.target.name]:e.target.value})
        }
        const submit =async (e) =>
        {
            if(npsw!=cpsw){
                alert("password not matched")
            }
            else{
                e.preventDefault();
                try{
                    const result=await axios.put(`http://localhost:8081/api/user/update/${npsw}`,form,{withCredentials:true})
                    console.log(result)
                    console.log(result.data)
                    alert(result.data)
                    nav("/log")
                }
                catch(err){
                    console.log(err)
                }
            }
       
    }
    return (
        <>
      <div className="page-section">
            <div className="home-card">
            <h2>Change password</h2>
            <div className="home-container">
        {/* <p>email:{form.email}</p>
        <p>password:{form.password}</p> */}
        <form onSubmit={submit}>
            <input onChange={change} type="email" name="email" placeholder="enter email"/>
            <input onChange={(e)=>{setnpsw(e.target.value)}} type="password" name="new password" placeholder="enter newpassword"/>
            <input onChange={(e)=>{setcpsw(e.target.value)}} type="password" name="confirm password" placeholder="again enter password"/>
            <button type="submit">Update</button>
        </form>
        </div>
        </div>
        </div>
        </>
            
    )
    
}
export default ForgetPass