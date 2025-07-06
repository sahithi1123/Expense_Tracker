import axios from 'axios'
import { useState } from 'react';
import { useNavigate } from 'react-router-dom';
function Updpro () {
    const nav=useNavigate()
    const prod=JSON.parse(localStorage.getItem("prod"))
    const [form,setform]=useState({
        firstName:prod.firstName,
        lastName:prod.lastName,
        username:prod.username,
        email:prod.email,
        phoneNum:prod.phoneNum,
        gender:prod.gender
    })
    const change =(e) =>
    {
        setform({...form,[e.target.name]:e.target.value})
    }
    const submit = async(e) =>
    {
        e.preventDefault();
        console.log(form.category)
        try{
            const res=await axios.put(`http://localhost:8081/api/user/updpro/${prod.id}`,form,{withCredentials:true})
            console.log(res)
            alert("updated sucessfully")
            nav('/profile')
        }
        catch(err){
            console.log(err);
        }
    }
    return (
        <>
       <div className="page-section">
            <div className="home-card">
            <h2>Update profile</h2>
            <div className="home-container">
               
        {/* <p>username:{form.username}</p>
         <p>email:{form.email}</p>
        <p>password:{form.password}</p> */}
        <form onSubmit={submit}>
            {/* <label>FirstName</label> */}
            <input onChange={change}placeholder="First name" type="text" name="firstName" value={form.firstName}/>
            {/* <label>LastName</label> */}
            <input onChange={change}placeholder="Last name" type="text" name="lastName" value={form.lastName}/>
            {/* <label>username</label> */}
            <input onChange={change}placeholder="Create username" type="text" name="username" value={form.username}/>
            {/* <label>email</label> */}
            <input onChange={change}placeholder="Enter email" type="email" name="email" value={form.email}/>
            {/* <label>PhoneNumber</label> */}
            <input onChange={change}placeholder="Enter phonenumber" type="tel" name="phoneNum" value={form.phoneNum} />
            {/* <label>Gender</label> */}
            <select name="gender" onChange={change} value={form.gender} required>
                <option value="male">male</option>
                <option value="female">female</option>
                <option value="others">others</option>
            </select><br/>
            <button type="submit">Update</button>
        </form>
        </div>
        </div>
        </div>
        </>
    )
}

export default Updpro
