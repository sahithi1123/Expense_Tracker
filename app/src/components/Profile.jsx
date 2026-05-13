import axios from "axios";
import { useEffect, useState } from "react"
import { useNavigate } from "react-router-dom";
import { toast } from "react-toastify";

function Profile()
{
    const nav=useNavigate();
    const[profile,setprofile]=useState({});
    useEffect(() => {
        const fetchProfile = async() =>{
            try{
            const res=await axios.get("http://localhost:8081/api/user/profile",{withCredentials:true})
            console.log(res.data)
            setprofile(res.data)
            localStorage.setItem("prod",JSON.stringify(res.data))
        }
        catch(err)
        {
            // console.log(err)
            toast.error(err.response.data)
        }
        }
        fetchProfile()   
    },[])

    return (
        <>
        <div>
        <button className="back-button"onClick={()=>{nav('/mid')}}>←Back</button>
        <div className="profile-wrapper">
        <div className="profile-card">
        <h2>👤 User Profile</h2>
      <div>
        <p><strong>First Name:</strong>  {profile?.firstName}</p>
        <p><strong>Last Name:</strong> {profile?.lastName}</p>
        <p><strong>username:</strong> {profile?.username}</p>
        <p><strong>Email:</strong> {profile?.email}</p>
        <p><strong>Phone:</strong> {profile?.phoneNum}</p>
        <p><strong>gender:</strong> {profile?.gender}</p>
        <button onClick={() =>{nav('/upro')}}>Update</button>
      </div>
    </div>
    </div>
    </div>

        </>
    )
}
export default Profile