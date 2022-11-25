
import Card from '@mui/material/Card';
import CardContent from '@mui/material/CardContent';
import Typography from '@mui/material/Typography';
import Tooltip from '@mui/material/Tooltip';
import { useNavigate } from 'react-router-dom';
import classes from './Problem.module.css';

const Problem = ({ problem }) => {
  const navigate = useNavigate();
  return (
    <Card
      variant="outlined"
      onClick={() => navigate(`/test/${problem.id}`)}
      className={classes.card}
    >
      <CardContent>
        <Typography>
          {problem.id + '번'}
        </Typography>
        <Tooltip title={problem.title || "-" } placement="bottom">
          {
            (function() {
              if(problem.title.length > 30){
                //console.log('ok ====' + problem.title.length)
                return (
                  <Typography 
                    sx={{height:50 }} 
                    gutterBottom component="div"
                    >
                    문제: {problem.title = problem.title.substr(0,problem.title.length - (problem.title.length - 30)) + '...' || "-" }
                  </Typography>
                );
              }
              // console.log(problem.title.length)
              return (
                <Typography 
                  sx={{height:50 }} 
                  gutterBottom component="div"
                  >
                  문제: {problem.title || "-"}
                </Typography>
              );
              
              
            })()
          }
        </Tooltip>
        <Typography>Level : {problem?.level || '-'}</Typography>
        <Typography>분류: {problem.type || '-'}</Typography>
      </CardContent>
    </Card>
  );
};

export default Problem;

