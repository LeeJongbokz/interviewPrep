import Problem from './Problem';

import Table from '@mui/material/Table';
import TableHead from '@mui/material/TableHead';
import TableRow from '@mui/material/TableRow';
import TableCell from '@mui/material/TableCell';
import TableBody from '@mui/material/TableBody';

import { THEME_COLOR } from '../../global_variables';

export const TableCellColumnHead = ({body}) => {
  return <TableCell component="th" scope="column" sx={{ color: 'white' }}>{body}</TableCell>;
}

const ProblemList = ({ question }) => {
  return (
    <Table>
      <TableHead sx={{ backgroundColor: THEME_COLOR }}>
        <TableRow>
          <TableCellColumnHead body="no" /> 
          <TableCellColumnHead body="문제 내용" /> 
          <TableCellColumnHead body="분류" />
        </TableRow>
      </TableHead>
      <TableBody>
        {question.map(test => {
          return <Problem key={test.id} problem={test} />;
        })}
      </TableBody>
    </Table>
  );
  // return (
  //   <Grid container spacing={1} columns={12}>
  //     {question.map((test, index) => {
  //       return (
  //         <Grid
  //           item
  //           key={index}
  //           xs={12}
  //           sm={6}
  //           md={3}
  //           // sx={{ width: { xs: '100%', sm: '50%', md: '25%' } }}
  //         >
  //          <Problem key={test.id} problem={test} />;
  //         </Grid>
  //       );
  //     })}
  //   </Grid>
  // );
};

export default ProblemList;
