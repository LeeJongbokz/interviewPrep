import { useState, useEffect } from 'react';

import useHttpRequest from '../../../hook/use-http';

import Tabs from '@mui/material/Tabs';
import Tab from '@mui/material/Tab';
import LockSharpIcon from '@mui/icons-material/LockSharp';

const QuestionSectionHeader = ({ questionId, headerVal, setHeaderVal }) => {
  const [solved, setsolved] = useState(false);
  const { isLoading, sendGetRequest } = useHttpRequest();

  const changeHanlder = (e, value) => {
    setHeaderVal(value);
  };

  useEffect(() => {
    const solvedHandler = data => {
      setsolved(data.success);
    };
    sendGetRequest(`/answer/solution/check/${questionId}`, solvedHandler);
  }, [sendGetRequest, questionId]);

  return (
    <>
      {isLoading ? (
        <Tabs />
      ) : (
        <Tabs variant="fullWidth" centered value={headerVal} onChange={changeHanlder}>
          <Tab disableRipple={true} label="question" />
          <Tab disableRipple={true} label="reference" />
          {solved ? (
            <Tab disableRipple={true} label="solution" />
          ) : (
            <Tab
              label="solution"
              wrapped={true}
              sx={{ minHeight: 48 }}
              icon={<LockSharpIcon margin={0} sx={{ fontSize: 15 }} />}
              iconPosition="end"
              disabled
            />
          )}
          <Tab disableRipple={true} label="submission" />
        </Tabs>
      )}
    </>
  );
};
export default QuestionSectionHeader;
