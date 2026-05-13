import { useState } from "react"
import axios from 'axios'
import { useNavigate } from "react-router-dom"
import { toast } from "react-toastify";

function BudgetAdd() {

    const nav = useNavigate();

    const [form, setform] = useState({
        month: "",
        year: "",
        amount: ""
    })

    const change = (e) => {

        setform({
            ...form,
            [e.target.name]: e.target.value
        })
    }

    const submit = async (e) => {

        e.preventDefault();

        try {

            const res = await axios.post(
                "http://localhost:8081/api/budget/add",
                form,
                { withCredentials: true }
            )

            console.log(res);

            toast.success(res.data)

            nav('/mid')

        }
        catch (err) {

            console.log(err)

            toast.error(err.response.data)

            if (err.response.status === 409) {

                nav('/mid');
            }
        }
    }

    return (
        <>

            <button
                className="back-button"
                onClick={() => { nav('/mid') }}
            >
                Back
            </button>

            <div className="page-section">

                <div className="home-card">

                    <h2>💰 Add Budget</h2>

                    <div className="home-container">

                        <form onSubmit={submit}>

                            {/* MONTH SELECT */}

                            <select
                                name="month"
                                value={form.month}
                                onChange={change}
                            >

                                <option value="">
                                    Select Month
                                </option>

                                <option value="1">January</option>
                                <option value="2">February</option>
                                <option value="3">March</option>
                                <option value="4">April</option>
                                <option value="5">May</option>
                                <option value="6">June</option>
                                <option value="7">July</option>
                                <option value="8">August</option>
                                <option value="9">September</option>
                                <option value="10">October</option>
                                <option value="11">November</option>
                                <option value="12">December</option>

                            </select>

                            {/* YEAR */}

                            <input
                                type="number"
                                name="year"
                                placeholder="Year"
                                value={form.year}
                                onChange={change}
                            />

                            {/* AMOUNT */}

                            <input
                                onChange={change}
                                name="amount"
                                type="number"
                                step="0.01"
                                placeholder="Enter amount"
                                value={form.amount}
                            />

                            <button type="submit">
                                Submit
                            </button>

                        </form>

                    </div>

                </div>

            </div>

        </>
    )
}

export default BudgetAdd