//import styled from 'styled-components';
import Card from "@mui/material/Card";
import CardContent from "@mui/material/CardContent";
import Typography from "@mui/material/Typography";
import {Button} from '@mui/material';
const Problem = ({ problem }) => {
  return (
    <Card 
      variant="outlined"
    >
      <CardContent>
        <Typography>
          {problem.id +1+ '번'}
        </Typography>
        <Typography sx={{height:40 }} gutterBottom component="div">
          문제: {problem.title || "-" }
        </Typography>
        <Typography>
          Level : {problem?.level || "-" }
        </Typography>
        <Typography>
          분류: {problem.type || "-"}
        </Typography>      
        <Button size="small" color="primary">
          문제 풀기
        </Button> 
      </CardContent>
    </Card>
  );
};

export default Problem;

// const Card = styled.div`
//   margin: 30px auto;
//   width: 20%;
//   border: 3px solid;
//   border-radius: 15px;
// `;

