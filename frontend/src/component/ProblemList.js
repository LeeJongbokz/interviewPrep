// import React, { useEffect,useState} from 'react';
import Problem from './Problem';
import Grid from '@mui/material/Unstable_Grid2';

const ProblemList = ({question}) => {
  //const [question, setQuestion] = useState([]);//map은 array만 됨
  // useEffect(() => {
  //   const fetchQuestion = async () => {
  //     setLoading(true);
  //     const target = searchtype === [] ? '' : `${searchtype}?page=0`
  //     //console.log(target)
  //     const response = await fetch(`http://52.202.27.18:8080/question/${target}`);

  //     if(!response.ok){
  //       throw new Error('Some Thing Went Error');
  //     }
  //     const data = await response.json();
  //     setQuestion(data.content);
  //   }
  //   fetchQuestion().catch((err) => {
  //     console.log(err)
  //   })
  //   setLoading(false);
  // }, [searchtype]);
  return (
    <Grid container spacing={1} columns={12}>
      {question.map((test, index) => {
        return (
          <Grid        
            item
            key={index}
            xs={12}
            sm={6}
            md={3}
            // sx={{ width: { xs: '100%', sm: '50%', md: '25%' } }}
          >
            <Problem
              sx={{ height: { xs: '250px', sm: '150px', md: '100px' } }}
              key={test.id}
              problem={test}
            />
          </Grid>
        );
      })}
    </Grid>
  );
};

export default ProblemList;
