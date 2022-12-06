import React, {ChangeEvent, useState, KeyboardEvent, useEffect} from 'react';
import axios, {head} from "axios";
import dayjs from "dayjs";
import {Button, TextField} from "@mui/material";
import './index.css';
import { styled } from '@mui/material/styles';
import {Simulate} from "react-dom/test-utils";
import error = Simulate.error;



function App() {

    const [getNoteList, setNoteList] = useState("");


    const getAllNotes = () => {
        axios.get("/home")
            .then((response) => {return response.data
            }).catch((error) => console.log("[Error von GET] =>" + error))
            .then((data) => {
            setNoteList(data)
        })
    }


  const [note, setNewNote] = useState("");

  const addNewNote = () => {
      axios.post("/api/myNotes", {text: note, date: dayjs().format("DD-MM-YYYY HH:mm:ss")})
          .then((response) => {
              return response.data
          }).catch((error) => console.log("Server is not responsing", error))
          .then(() => {
              setNote("")
          })
  }

    const handleChange = (event:ChangeEvent<HTMLInputElement>) => {
      setNote(event.target.value)
    }

    const handleClick = () => {
      addNewNote();
    }

    const handleClickDown = (event:KeyboardEvent<HTMLButtonElement>) => {
      if(event.key === "Enter"){
          addNewNote();
      }

    }
    return (
        <StyledContainer>
            <header>

            </header>
            <StyledTop>
                <TextField label="Write a new story..." variant="outlined"
                           value={note} onChange={handleChange} placeholder={""}/>
                <Button  variant="outlined" onClick={handleClick} onKeyDown={handleClickDown}>Add</Button>
            </StyledTop>
            <StyledDiv>
                <Button sx={{}} variant="contained">Login</Button>
                <Button variant="outlined">Sign in</Button>
            </StyledDiv>

        </StyledContainer>
    );
}

export default App;

const StyledDiv=styled("div")`
    justify-content: center;
    display: flex;
    position: center;
    flex-direction: column;
    align-content: center;
    padding: 20px 8px;
    gap: 5px;
`

const StyledContainer=styled("div")`
    height: 100vh;
    display: flex;
    flex-direction: column;
    justify-content: space-between;
    align-items: center;
    
`
const StyledTop=styled("div")`
    
    display: flex;
    position: center;
    flex-direction: column;
    
`
