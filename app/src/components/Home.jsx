

function Home() {
  return (
    <div className="welcome-page">
      <div className="welcome-card">
        <div className="welcome-text">
          <h1>Welcome to Expense Tracker</h1>
          <div className="home-buttons">
            <a href="/reg"><button>Sign Up</button></a>
            <a href="/log"><button>Login</button></a>
          </div>
        </div>
        <div className="welcome-image">
          <img src="https://cdn-icons-png.flaticon.com/512/5862/5862339.png" alt="Expense illustration" />
        </div>
      </div>
    </div>
  );
}
export default Home