import React, { useEffect, useState } from 'react';
import useHttpRequest from '../../hook/use-http';
import ProblemList from './ProblemList';
import Select from './Select';
import ContainerUI from '../UI/ContainerUI';
import Pagination from '@mui/material/Pagination';
import Box from '@mui/material/Box';
import LoadingSpinner from '../UI/LoadingSpinner';

const Test = () => {
  const [question, setQuestion] = useState([]);
  const [searchType, setSearchType] = useState(''); //type의 값을 설정
  const [totalPage, setTotalPage] = useState(0);
  const [page, setPage] = useState(0);

  const { isLoading, sendGetRequest } = useHttpRequest();

  useEffect(() => {
    const questionHandler = data => {
      setQuestion(data.data.content);
      setTotalPage(data.data.totalPages);
    };
    sendGetRequest(`/question/${searchType}?page=${page}`, questionHandler);
  }, [sendGetRequest, page, searchType]);

  const selectTypeHandler = event => {
    setSearchType(event.target.value);
    setPage(0);
  };

  const pageHandler = (event, value) => {
    setPage(value - 1);
  };

  return (
    <ContainerUI>
      <Box>
        <Select onSelect={selectTypeHandler} searchType={searchType} />
      </Box>
      <Box>
        {isLoading && <LoadingSpinner />}
        {!isLoading && <ProblemList question={question} />}
      </Box>
      <Box margin={5} display="flex" justifyContent="center" alignItems="center">
        {!isLoading && (
          <Pagination page={page + 1} count={totalPage} onChange={pageHandler} color="primary" />
        )}
      </Box>
    </ContainerUI>
  );
};

export default Test;
