// import Card from '@mui/material/Card';
// import CardContent from '@mui/material/CardContent';
// import Typography from '@mui/material/Typography';
// import { Button } from '@mui/material';
// import Tooltip from '@mui/material/Tooltip';
import { useNavigate } from 'react-router-dom';
import classes from './Problem.module.css';

import TableRow from '@mui/material/TableRow';
import TableCell from '@mui/material/TableCell';

const Problem = ({ problem }) => {
  const navigate = useNavigate();
  return (
    <TableRow hover className={classes.row} onClick={() => navigate(`/test/${problem.id}`)}>
      <TableCell component="th" scope="row">{problem.id}</TableCell>
      <TableCell>{problem.title}</TableCell>
      <TableCell>{problem.type || '-'}</TableCell>
    </TableRow>
  );
  // return (
  //   <Card variant="outlined" className={classes.card} onClick={() => navigate(`/test/${problem.id}`)}>
  //     <CardContent>
  //       <Typography>{problem.id}번</Typography>
  //       <Tooltip title={problem.title || '-'} placement="bottom">
  //         <Typography
  //           sx={{
  //             display: '-webkit-box',
  //             overflow: 'hidden',
  //             WebkitBoxOrient: 'vertical',
  //             WebkitLineClamp: 2,
  //             height: '50px',
  //           }}
  //         >
  //           문제: {problem.title || '-'}
  //         </Typography>
  //       </Tooltip>
  //       <Typography>Level : {problem?.level || '-'}</Typography>
  //       <Typography>분류: {problem.type || '-'}</Typography>
  //     </CardContent>
  //   </Card>
  // );
};

export default Problem;
