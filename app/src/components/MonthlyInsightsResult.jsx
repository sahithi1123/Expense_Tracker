import React, { useEffect, useState } from "react";
import axios from "axios";
import { useLocation, useNavigate } from "react-router-dom";

function MonthlyInsightsResult() {

    const location = useLocation();
    const navigate = useNavigate();

    const { month, year } = location.state;

    const [loading, setLoading] = useState(true);
    const [response, setResponse] = useState("");

    useEffect(() => {

        fetchInsights();

    }, []);

    const fetchInsights = async () => {

        try {

            const res = await axios.get(
                `http://localhost:8081/api/chat/minsights?month=${month}&year=${year}`,
                {
                    withCredentials: true
                }
            );

            setResponse(res.data);

        } catch (error) {

            console.log(error);
            setResponse("Failed to fetch insights");

        } finally {

            setLoading(false);
        }
    };

    return (

        <div style={styles.page}>

            <button
                style={styles.backButton}
                onClick={() => navigate("/minsights")}
            >
                Back
            </button>

            {
                loading ? (

                    <div style={styles.loadingContainer}>

                        <div style={styles.spinner}></div>

                        <h2 style={styles.loadingText}>
                            AI is analyzing your monthly expenses...
                        </h2>

                    </div>

                ) : (

                    <div style={styles.outputContainer}>

                        <h1 style={styles.heading}>
                            📊 Monthly AI Financial Insights
                        </h1>

                        <pre style={styles.outputText}>
                            {response}
                        </pre>

                    </div>
                )
            }

        </div>
    );
}

const styles = {

    page: {
        minHeight: "100vh",
        background:
            "linear-gradient(to right, #5a4b81, #7b6aa8)",
        padding: "50px",
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
        fontWeight: "bold",
        fontSize: "15px"
    },

    loadingContainer: {
        minHeight: "100vh",
        display: "flex",
        flexDirection: "column",
        justifyContent: "center",
        alignItems: "center",
        gap: "20px"
    },

    spinner: {
        width: "70px",
        height: "70px",
        border: "7px solid rgba(255,255,255,0.3)",
        borderTop: "7px solid white",
        borderRadius: "50%",
        animation: "spin 1s linear infinite"
    },

    loadingText: {
        color: "white",
        fontSize: "25px",
        fontWeight: "bold"
    },

    outputContainer: {
        width: "100%",
        maxWidth: "1200px",
        margin: "40px auto",
        backgroundColor: "#817ca8",
        padding: "40px",
        borderRadius: "20px",
        color: "white"
    },

    heading: {
        marginBottom: "35px",
        fontSize: "42px",
        textAlign: "center"
    },

    outputText: {
        whiteSpace: "pre-wrap",
        lineHeight: "2.2",
        fontSize: "19px",
        fontFamily: "Segoe UI"
    }
};

export default MonthlyInsightsResult;