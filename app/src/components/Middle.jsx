import { Link } from 'react-router-dom';

function Middle() {
  return (
    <>
 <div className="navbar">
  <div className="nav-left">
    <h2>Smart Expense Tracker</h2>
  </div>
  <div className="nav-right">
    <a href="/">Home</a>
    <a href="/about">About</a>
    <a href="/profile">Profile</a>
    <a href="/log" className="logout">Logout</a>
  </div>
</div>
 


<div className="dashboard-container">
  <div className="main-dashboard-content">
    <h1>Welcome</h1>
  </div>
  <div className="dashboard-sidebar">
    <Link to='/addexp'><button>Add Expense</button></Link>
    <Link to='/myexp'><button>My Expenses</button></Link>
    <Link to='/addbud'><button>Add Budget</button></Link> 
     <Link to='/mybud'><button>My Budgets</button></Link>
  </div>
</div>

    </>
  )
}

export default Middle