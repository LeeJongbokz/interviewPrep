import { useState, useEffect, useContext } from 'react';
import AuthContext from '../../store/auth-context';
import { BACKEND_BASE_URL } from '../../global_variables';

import Tabs from '@mui/material/Tabs';
import Tab from '@mui/material/Tab';
import LockSharpIcon from '@mui/icons-material/LockSharp';

const QuestionSectionHeader = ({ questionId, headerVal, setHeaderVal }) => {
  const [solved, setsolved] = useState(false);
  const [isLoading, setIsLoading] = useState(true);

  const changeHanlder = (e, value) => {
    setHeaderVal(value);
  };

  const authCtx = useContext(AuthContext);

  useEffect(() => {
    const fetchSolved = async () => {
      const response = await fetch(`${BACKEND_BASE_URL}/answer/solution/check/${questionId}`, {
        method: 'GET',
        headers: {
          accessToken: authCtx.token,
          refreshToken: authCtx.refreshToken,
        },
      });

      if (!response.ok) {
        throw new Error('Some Thing Went Error');
      }

      const data = await response.json();
      setsolved(data.success);
      setIsLoading(false);
    };

    fetchSolved().catch(err => {
      setIsLoading(false);
      console.log(err);
    });
  }, [solved, questionId, authCtx.token, authCtx.refreshToken]);

  return (
    <>
      {isLoading ? <Tabs /> : (
        <Tabs variant="fullWidth" centered value={headerVal} onChange={changeHanlder}>
          <Tab label="question" />
          <Tab label="reference" />
          {solved ? (
            <Tab label="solution" />
          ) : (
            <Tab
              label="solution"
              wrapped={true} 
              sx={{ minHeight:48 }}
              icon={<LockSharpIcon margin={0} sx={{ fontSize: 15 }} />}
              iconPosition="end"
              disabled
            />
          )}
          <Tab label="submission" />
        </Tabs>
      )}
    </>
  );
};
export default QuestionSectionHeader;
