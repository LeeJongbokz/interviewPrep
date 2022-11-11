import React, { useEffect, useState } from 'react';
import Problem from './Problem';
import Select from './Select';
//import Card from './Card';
import Grid from '@mui/material/Unstable_Grid2';
import ContainerUI from './UI/ContainerUI';

const Test = () => {

  const [question, setQuestion] = useState([]);//map은 array만 됨
  
  useEffect(() => {
    const fetchQuestion = async () => {
      
      //console.log('api연동성공');
      const response = await fetch(`http://52.202.27.18:8080/question/`);

      if(!response.ok){
        throw new Error('Some Thing Went Error');
      }
      const data = await response.json();
      setQuestion(data.content);
      //console.log(data);

    }
    fetchQuestion().catch((err) => {
      console.log(err)
    })
  }, []);

  return (
    <ContainerUI>
      <div>
        <Select categories={question}/>
      </div>
      <div>
      <Grid 
        container 
        spacing={1} 
        columns={{ xs:12 }}
      >
        {question.map((test, index) => {
            return (
              <Grid key={index} item xs={12} sm={6} md={3} sx={{width:{xs:"100%", sm:"50%", md:"25%"}}}>
                <Problem sx={{height:{xs:"250px", sm:"150px", md:"100px"}  }} problem={test} />
              </Grid>
            )
          })}
      </Grid>
      </div>
    </ContainerUI>
  );
};

export default Test;
