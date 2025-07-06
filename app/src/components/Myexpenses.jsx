import axios from 'axios'
import { useEffect, useState } from 'react'
import { useNavigate } from 'react-router-dom'
function Myexpenses() {
    const nav=useNavigate();
    const [expenses,setExpenses]=useState([]);
    const [page,setPage]=useState(0);
    const [totalpages,setTotalPages]=useState(0)

    const getData = async() =>{
        try{
            const res=await axios.get(`http://localhost:8081/api/expense?page=${page}&size=5`,{withCredentials:true})
            setExpenses(res.data.content);
            console.log(res)
            console.log(res.data.totalPages)
            setTotalPages(res.data.totalPages)
        }
        catch(err){
            console.error("failed to fetech students",err)
        }
    }
    useEffect( () =>{
        getData();
    },[page]);

    const del= async (id) =>
    {
        try{
            const res=await axios.delete(`http://localhost:8081/api/expense/delete/${id}`,{withCredentials:true})
            console.log(res)
            alert("deleted..")
            getData();
        }
        catch (err)
        {
            console.log(err)
        }
    }

    const update = async(d) =>
    {   
        // console.log("data is",d)
        localStorage.setItem("data",JSON.stringify(d));
        nav('/upd')
    }
    return (
        <>
        <h2>Expenses list</h2>
        <table border="2">
            <thead>
                <tr>
                    <th>Id</th>
                    <th>Amount</th>
                    <th>Category</th>
                    <th>Date</th>
                    <th>Description</th>
                </tr>
            </thead>
            <tbody>
                {
                    expenses.map((s) => {
                    return (
                    <tr key={s.id}>
                        <td>{s.id}</td>
                        <td>{s.amount}</td>
                        <td>{s.category}</td>
                        <td>{s.date}</td>
                        <td>{s.description}</td>
                        <td>
                            <button onClick={() => del(s.id)}>Delete</button>
                            <button onClick={() => update(s)}>Update</button>
                        </td>
                    </tr>
                    );
                    })
                }
            </tbody>
        </table>
        <div style={{marginTop:"10px"}}>
                <button onClick={() => setPage((p) => Math.max(p-1,0))} disabled={page==0}>prev</button>
                <span style={{marginTop:"0 10px"}}>Page:{page+1}</span>
                <button onClick={() => setPage((p) => p+1)} disabled={totalpages-1==page}>Next</button>
        </div>

        </>
    )
}

export default Myexpenses

