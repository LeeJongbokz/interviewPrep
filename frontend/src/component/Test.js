import React, { useEffect, useState } from 'react';
import { useNavigate } from 'react-router-dom';
import Problem from './Problem';
import Select from './Select';
//import Card from './Card';
import Container from '@mui/material/Container';
import Grid from '@mui/material/Unstable_Grid2';

const Test = () => {

  const [question, setQuestion] = useState([]);//map은 array만 됨
  const navigate = useNavigate();
  
  useEffect(() => {
    const fetchQuestion = async () => {
      
      //console.log('api연동성공');
      const response = await fetch(`https://react-post-de8f7-default-rtdb.firebaseio.com/interviewPrep/question.json`);

      if(!response.ok){
        throw new Error('Some Thing Went Error');
      }
      const data = await response.json();
      setQuestion(data);
      //console.log(data);

    }
    fetchQuestion().catch((err) => {
      console.log(err)
    })
  }, []);


  return (
    <Container>
      <div>
        <Select/>
      </div>
      <div>
      <Grid 
        container 
        spacing={1} 
        columns={{ xs:12 }}
      >
        {question.map((test, index) => {
            return (
              <Grid key={index} item xs={12} sm={6} md={3} sx={{width:{xs:"100%", sm:"50%", md:"25%"}}} onClick={() => navigate(`/test/${test.id}`)}>
                <Problem sx={{height:{xs:"250px", sm:"150px", md:"100px"}  }} problem={test} />
              </Grid>
            )
          })}
      </Grid>
      </div>
    </Container>
  );
};

export default Test;
