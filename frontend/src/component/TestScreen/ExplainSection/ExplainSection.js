import { useState } from 'react';
import ExplainSectionHeader from './ExplainSectionHeader';
import QuestionField from './Question/QuestionField';
import DiscussionSection from './Reference/Reference';
import SolutionList from './Solution/SolutionList';

import Box from '@mui/material/Box';

const ExplainSection = ({ questionId }) => {
  const [headerVal, setHeaderVal] = useState(0);

  return (
    <>
      <ExplainSectionHeader
        questionId={questionId}
        headerVal={headerVal}
        setHeaderVal={setHeaderVal}
      />
      <Box
        padding={2}
        sx={{
          height: { md: '75vh' },
          overflow: { md: 'auto' },
        }}
      >
        {headerVal === 0 && <QuestionField questionId={questionId} />}
        {headerVal === 1 && <DiscussionSection />}
        {headerVal === 2 && <SolutionList />}
        {headerVal === 3 && <>SUBMISSION</>}
      </Box>
    </>
  );
};
export default ExplainSection;
