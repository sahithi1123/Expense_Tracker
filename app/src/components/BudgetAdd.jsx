import { useState } from "react"
import axios from 'axios'
import { useNavigate } from "react-router-dom"
function BudgetAdd() {
    const nav=useNavigate();
    const [form,setform]=useState({
        month:"",
        year:"",
        amount:""
    })
    const change =(e) =>
    {
        setform({...form,[e.target.name]:e.target.value})
    }
    const submit=async(e) =>{
        e.preventDefault();
        try{
            const res=await axios.post("http://localhost:8081/api/budget/add",form,{withCredentials:true})
            console.log(res);
            alert(res.data)
            nav('/mid')
        }
        catch(err)
        {
            console.log(err)
            alert(err.response.data);
        }
    }
    return (
        <>
        <div className="page-section">
            <div className="home-card">
            <h2>💰 Welcome to budget page</h2>
            <div className="home-container">
                 
        <form onSubmit={submit}>
            {/* <label>month</label> */}
            <input type="number" name="month" placeholder="Month number" min="1" max="12"value={form.month} onChange={change}/><br/>
            {/* <label>year</label> */}
            <input type="year" name="year" placeholder="Year" value={form.year} onChange={change}/><br/>
            {/* <label>amount</label> */}
            <input onChange={change} name="amount" type="number" step="0.01" placeholder="Enter amount" value={form.amount}/>
            <button type="submit">submit</button>
        </form>
        </div>
        </div>
        </div>
        </>
    )
}

export default BudgetAdd


