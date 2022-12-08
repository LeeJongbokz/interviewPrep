import TableRow from '@mui/material/TableRow';
import { useNavigate } from 'react-router-dom';
import TableCell from '@mui/material/TableCell';
import TableBody from '@mui/material/TableBody';
import classes from '../Test/Problem.module.css';


const RecentProblem = () => {
  const navigate = useNavigate();
  return (
  <TableBody>   
    <TableRow hover className={classes.row} onClick={() => navigate(`/test/1${'#'}`)}>
      <TableCell component="th" scope="row">1</TableCell>
      <TableCell>test</TableCell>
      <TableCell>비고</TableCell>
    </TableRow>
    <TableRow hover className={classes.row} >
      <TableCell component="th" scope="row">2</TableCell>
      <TableCell>test</TableCell>
      <TableCell>비고</TableCell>
    </TableRow>
  </TableBody>
  );
};

export default RecentProblem;
