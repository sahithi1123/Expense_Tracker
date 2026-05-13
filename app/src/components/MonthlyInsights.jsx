import React, { useState } from "react";
import { useNavigate } from "react-router-dom";

function MonthlyInsights() {

    const navigate = useNavigate();

    const [month, setMonth] = useState("");
    const [year, setYear] = useState("");

    const months = [
        { name: "January", value: 1 },
        { name: "February", value: 2 },
        { name: "March", value: 3 },
        { name: "April", value: 4 },
        { name: "May", value: 5 },
        { name: "June", value: 6 },
        { name: "July", value: 7 },
        { name: "August", value: 8 },
        { name: "September", value: 9 },
        { name: "October", value: 10 },
        { name: "November", value: 11 },
        { name: "December", value: 12 }
    ];

    const handleGenerate = () => {

        navigate("/mresult", {
            state: {
                month,
                year
            }
        });
    };

    return (

        <div style={styles.page}>

            <button
                style={styles.backButton}
                onClick={() => navigate("/mid")}
            >
                Back
            </button>

            <div style={styles.card}>

                <h1 style={styles.heading}>
                    🤖 Monthly AI Insights
                </h1>

                <div style={styles.form}>

                    <select
                        value={month}
                        onChange={(e) => setMonth(e.target.value)}
                        style={styles.input}
                    >

                        <option value="">
                            Select Month
                        </option>

                        {
                            months.map((m) => (
                                <option
                                    key={m.value}
                                    value={m.value}
                                >
                                    {m.name}
                                </option>
                            ))
                        }

                    </select>

                    <input
                        type="number"
                        placeholder="Enter Year"
                        value={year}
                        onChange={(e) => setYear(e.target.value)}
                        style={styles.input}
                    />

                    <button
                        style={styles.button}
                        onClick={handleGenerate}
                    >
                        Generate Insights
                    </button>

                </div>

            </div>

        </div>
    );
}

const styles = {

    page: {
        minHeight: "100vh",
        background:
            "linear-gradient(to right, #6b5b95, #878f99)",
        display: "flex",
        justifyContent: "center",
        alignItems: "center",
        position: "relative"
    },

    backButton: {
        position: "absolute",
        top: "20px",
        right: "20px",
        backgroundColor: "#4f3f79",
        color: "white",
        border: "none",
        padding: "10px 18px",
        borderRadius: "8px",
        cursor: "pointer",
        fontWeight: "bold"
    },

    card: {
        width: "100%",
        maxWidth: "500px",
        backgroundColor: "white",
        padding: "20px",
        borderRadius: "20px",
        boxShadow: "0 10px 30px rgba(0,0,0,0.25)"
    },

    heading: {
        textAlign: "center",
        color: "#5f4b8b",
        fontSize: "40px",
        marginBottom: "30px"
    },

    form: {
        display: "flex",
        flexDirection: "column",
        gap: "20px"
    },

    input: {
        padding: "15px",
        borderRadius: "10px",
        border: "1px solid #c9b6ff",
        fontSize: "16px"
    },

    button: {
        padding: "15px",
        backgroundColor: "#6b5b95",
        color: "white",
        border: "none",
        borderRadius: "10px",
        fontSize: "17px",
        fontWeight: "bold",
        cursor: "pointer"
    }
};

export default MonthlyInsights;