import { useState, useEffect } from "react";

export const useFetch = () => {
    const [data, setData] = useState(null);
    const [loading, setLoading] = useState(false);
    const [errorMessage, setErrorMessage] = useState("");

    const httpConfig = async (requestData, method, url) => {
        setLoading(true);
        setErrorMessage("")

        const options = {
            method,
            headers: {
                "Content-Type": "application/json"
            },
            body: method === "POST" || method === "PATCH" || method === "DELETE" || method === "PUT" ? JSON.stringify(requestData) : null
        };
        try {
            const res = await fetch(url, options);

            if (!res.ok) {
                let errorData;
                const contentType = res.headers.get("content-type");

                if (contentType && contentType.includes("application/json")) {
                    errorData = await res.json().catch(() => ({ mensagem: "Erro na requisição" }));
                } else {
                    errorData = { mensagem: await res.text() || "Erro na requisição" };
                }

                throw {
                    message: errorData.mensagem,
                    status: res.status,
                };
            }

            const contentType = res.headers.get("content-type");
            if (contentType && contentType.includes("application/json")) {
                const responseData = await res.json();
                setData(responseData);
                return responseData;
            } else {
                const textData = "Alteração bem sucedida";
                setData(textData);
                return textData;
            }
        } catch (error) {
            if (typeof error === 'object' && error !== null && error.message) {
                setErrorMessage(error.message);
            } else {
                setErrorMessage("Erro desconhecido.");
            }
            return null;
        } finally {
            setLoading(false);
        }
    };

    const fetchData = async (url) => {
        setLoading(true);
        setErrorMessage("");

        const res = await fetch(url);

        const json = await res.json();
        setData(json);

        setLoading(false);

    };

    return { data, httpConfig, fetchData, loading, errorMessage };


};







