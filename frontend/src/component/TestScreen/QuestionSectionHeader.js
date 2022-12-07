import Tabs from '@mui/material/Tabs';
import Tab from '@mui/material/Tab';
import LockIcon from '@mui/icons-material/Lock';

const QuestionSectionHeader = ({ headerVal, setHeaderVal, solved=false}) => {

  const changeHanlder = (e, value) => {
    setHeaderVal(value);
  }

  return (
    <Tabs variant='fullWidth' centered value={headerVal} onChange={changeHanlder}>
      <Tab label="question" />
      <Tab label="discussion" />
      {solved && <Tab label="solution" />}
      {solved || <Tab icon={<LockIcon fontSize="small" />} iconPosition="end" label="solution" disabled />}
      <Tab label="submission" />
    </Tabs>
  );
};
export default QuestionSectionHeader;
