import React, { useEffect,useState} from 'react';
import Problem from './Problem';
import Skeleton from '@mui/material/Skeleton';
import Grid from '@mui/material/Unstable_Grid2';

const ProblemList = ({searchtype}) => {
  const [question, setQuestion] = useState([]);//map은 array만 됨
  const [loading, setLoading] = useState(false);

  useEffect(() => {
    const fetchQuestion = async () => {
      setLoading(true);
      const target = searchtype === [] ? '' : `${searchtype}?page=0`
      //console.log(target)
      const response = await fetch(`http://52.202.27.18:8080/question/${target}`);

      if(!response.ok){
        throw new Error('Some Thing Went Error');
      }
      const data = await response.json();
      setQuestion(data.content);
    }
    fetchQuestion().catch((err) => {
      console.log(err)
    })
    setLoading(false);
  }, [searchtype]);

  return (
    <Grid 
        container 
        spacing={1} 
        columns={{ xs:12 }}
      >
        {question.map((test, index) => {
            return (
              <Grid key={index} item xs={12} sm={6} md={3} sx={{width:{xs:"100%", sm:"50%", md:"25%"}}}>
                { loading && (
                  //  요청이 완료전, 즉 loading 값이 true 이면 해당 UI 띄우기
                  <Skeleton variant="rounded" sx={{height:{xs:"300px", sm:"200px", md:"150px"}  }} ></Skeleton>
                )}
                { !loading && (
                  <Problem sx={{height:{xs:"250px", sm:"150px", md:"100px"}  }} key={test.id} problem={test} />
                )}
              </Grid>
            )
          })}
      </Grid>
  );
};

export default ProblemList;

