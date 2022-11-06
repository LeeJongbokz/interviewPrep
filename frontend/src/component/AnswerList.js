import { useEffect, useState } from "react";
import Container from "@mui/material/Container";
import { List, ListItem, ListItemText } from "@mui/material";

const AnswerList = () => {

  const [answerArray, setAnswerArray] = useState([]);
  useEffect(() => {

    const response = {
      "-NFqZQ2WJDJqLmdZ6wX2" : {
        "answer" : "TCP 는 응답 핑을 받고 나서 통신을 하지만, UDP 는 핑을 받지 않고 바로 전송한다.",
        "testId" : "1"
      },
      "-NFqkRUmaVI3J0GM0zdo" : {
        "answer" : "TCP 는 응답 핑을 받고 나서 통신을 하지만, UDP 는 핑을 받지 않고 바로 전송한다",
        "testId" : "1"
      }
    }

    const answers = [];
    for( const key in response){
      answers.push({
        id: key,
        answer: response[key].answer
      })
    }
    setAnswerArray(answers);
    console.log(answers);
  }, []);

  return (
  <Container>
    <List sx={{bgcolor: 'white'}}> 
    {
      answerArray.map((item) => {
        return <ListItem alignItems="flex-start" key={item.id}>
          <ListItemText
            sx={{ display: 'inline' }}
            primary={item.answer}
          />
        </ListItem>
        
      })  
    }
    </List>
  </Container>)

} 

export default AnswerList;