import React, { useEffect, useState } from 'react';
import ProblemList from './ProblemList';
import Select from './Select';
import ContainerUI from '../UI/ContainerUI';
// import Skeleton from '@mui/material/Skeleton';
import Pagination from '@mui/material/Pagination';
import Box from '@mui/material/Box';
import CircularProgress from '@mui/material/CircularProgress'

const Test = () => {
  const [question, setQuestion] = useState([]);
  const [searchType, setSearchType] = useState('all'); //type의 값을 설정
  const [loading, setLoading] = useState(true);
  const [totalPage, setTotalPage] = useState(0);
  const [page, setPage] = useState(0);

  //선택했을 때 카테고리 값을 받아와서 업데이트 해줄 것
  // const selectTypeHandler = useCallback((event) => {
  //   const searchtype = event.target.value;
  //   setsearchtype(searchtype);
  // }, []);

  const selectTypeHandler = event => {
    setSearchType(event.target.value);
    setPage(0);
  };

  const pageHandler = (event, value) => {
    setPage(value - 1);
  };

  useEffect(() => {
    const fetchQuestion = async () => {
      setLoading(true);
      setQuestion([]);
      // const target = searchType === [] ? '' : `${searchType}?page=${page}`;
      const response = await fetch(`http://52.202.27.18:8080/question/${searchType}?page=${page}`);

      if (!response.ok) {
        throw new Error('Some Thing Went Error');
      }
      const data = await response.json();
      setQuestion(data.content);
      setTotalPage(data.totalPages);
      setLoading(false);
    };

    fetchQuestion().catch(err => {
      console.log(err);
      setLoading(false);
    });
  }, [searchType, page]);

  return (
    <ContainerUI>
      <Box>
        <Select
          // categories={question}
          onSelect={selectTypeHandler}
          searchType={searchType}
        />
      </Box>
      <Box>
        {loading && <CircularProgress />
          // <Skeleton variant="rounded" sx={{ height: { xs: '300px', sm: '200px', md: '150px' } }}>
          //   LOADING!
          // </Skeleton>
        }
        {!loading && <ProblemList question={question} />}
      </Box>
      <Box margin={5} display="flex" justifyContent="center" alignItems="center">
        <Pagination page={page + 1} count={totalPage} onChange={pageHandler} sx={{color:'#ff4b4e' }}/>
      </Box>
    </ContainerUI>
  );
};

export default Test;
