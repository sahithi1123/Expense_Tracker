import axios from 'axios'
import { useState } from 'react'
import { useNavigate } from 'react-router-dom'
function Addexpense() {
    const nav=useNavigate()
    const today=new Date().toISOString().split("T")[0];
    const[form,setform]=useState({
                amount:"",
                category:"food",
                date:"",
                description:""
            }
    )
    const change =(e) =>
    {
        setform({...form,[e.target.name]:e.target.value})
    }
    const submit=async(e) =>{
        e.preventDefault();
        try{
            const res=await axios.post("http://localhost:8081/api/expense/add",form,{withCredentials:true})
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
              <h2>welcome Expenses page</h2>
            <div className="home-container">
          
        <form onSubmit={submit}>
            {/* <p>amount:{form.amount}</p>
            <p>category:{form.category}</p>
            <p>date:{form.date}</p>
            <p>description:{form.description}</p> */}
            {/* <label>amount</label> */}
            <input onChange={change} name="amount" type="number" step="0.01" placeholder="Enter amount" value={form.amount}/>
            {/* <label>category</label> */}
             <select name="category" onChange={change} required>
                <option value="food">🍔 Food</option>
                <option value="rent">🏠 Rent</option>
                <option value="Utilities">💡 Utilities</option>
                <option value="Groceries">🛒 Groceries</option>
                <option value="Transportation">🚗 Transportation</option>
                <option value="Insurance">🛡️ Insurance</option>
                <option value="Medical">💊 Medical</option>
                <option value="Loan Payments">💸 Loan Payments</option>
                <option value="others">📁 Others</option>
            </select>
            {/* <label>date</label> */}
            <input onChange={change}placeholder="select date" type="date" name="date" value={form.date} max={today} />  
            {/* <label>Description</label> */}
            <input onChange={change}placeholder="Enter description" type="text" name="description" value={form.description}/>
            <button type="submit">add</button> 
        </form>
        </div>
        </div>
        </div>
        </>
    )
}

export default Addexpense


