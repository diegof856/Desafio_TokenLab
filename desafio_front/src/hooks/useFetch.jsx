import { useState, useEffect } from "react";

export const useFetch = (url) => {
    const [data, setData] = useState(null);
    const [loading, setLoading] = useState(false);
   
    const httpConfig = async (requestData, method) => {
        setLoading(true);
        const options = {
            method,
            headers: {
                "Content-Type": "application/json"
            },
            body: method === "POST" ? JSON.stringify(requestData) : null
        };

        const res = await fetch(url, options);
        const responseData = await res.json();
        setData(responseData);
        setLoading(false);
        return responseData; 
    };

    useEffect(() => {
        const fetchData = async () => {
            setLoading(true);
            const res = await fetch(url);
            const json = await res.json();
            setData(json);
            setLoading(false);
        };
        fetchData();
    }, [url]);

    return { data, httpConfig, loading };
};