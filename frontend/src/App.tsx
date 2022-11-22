import React, {useEffect, useState} from 'react';
import './App.css';
import axios from "axios";

function App() {

  const [message, setMessage] = useState<string>();

  useEffect( () => {
    axios.get("/api",)
        .then(responce => responce.data)
        .then(setMessage)
  }, [])

  return <>
    {message}
    </>;
}

export default App;
