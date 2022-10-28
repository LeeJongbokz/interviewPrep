//import styled from 'styled-components';
import Card from "@mui/material/Card";
import CardContent from "@mui/material/CardContent";
import Typography from "@mui/material/Typography";

const Problem = ({ problem }) => {
  return (
    <Card 
      variant="outlined"
    >
      <CardContent>
        <Typography>
          분류: {problem[0]}
        </Typography>       
        <Typography>
          문제: {problem[1]}
        </Typography>
        <Typography>
          문제 난이도 : {problem[2]}
        </Typography>
        <Typography>
        푼 사람 수: {problem[3]}
        </Typography>
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

