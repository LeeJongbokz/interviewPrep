import Tabs from '@mui/material/Tabs';
import Tab from '@mui/material/Tab';

const QuestionSectionHeader = ({ headerVal, setHeaderVal}) => {

  const changeHanlder = (e, value) => {
    setHeaderVal(value);
  }
  return (
    <Tabs variant='fullWidth' centered value={headerVal} onChange={changeHanlder}>
      <Tab label="question" />
      <Tab label="discussion" />
      <Tab label="solutions" />
      <Tab label="submission" />
    </Tabs>
  );
};
export default QuestionSectionHeader;
