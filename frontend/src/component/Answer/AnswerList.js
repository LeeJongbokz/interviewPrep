import { useEffect, useState } from "react";
import { useParams } from 'react-router-dom';
import Container from "@mui/material/Container";
import { List, ListItem, ListItemText } from "@mui/material";

const AnswerList = () => {

  const { id:testId } = useParams();
  const [answerArray, setAnswerArray] = useState([]);
  useEffect(() => {

    const fetchAnswer = async () => {
      
      const response = await fetch(`https://react-post-de8f7-default-rtdb.firebaseio.com/interviewPrep/answer.json?orderBy=%22testId%22&equalTo="${testId}"`);
      //console.log(testId);

      if(!response.ok){
        throw new Error('Some Thing Went Error');
      }
      const data = await response.json();
      const answers = [];
      for( const key in data){
        answers.push({
          id: key,
          answer: data[key].answer
        })
      }
      setAnswerArray(answers);  
      //console.log(data);
      
    }
    fetchAnswer().catch((err) => {
      console.log(err)
    })
    
  }, [testId]);

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