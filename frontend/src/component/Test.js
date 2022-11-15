import React, { useEffect, useState, useCallback } from 'react';
import ProblemList from './ProblemList';
import Select from './Select';
import ContainerUI from './UI/ContainerUI';

const Test = () => {
  const [question, setQuestion] = useState([]);
  const [searchtype, setsearchtype] = useState('all');//type의 값을 설정

  //선택했을 때 카테고리 값을 받아와서 업데이트 해줄 것
  const onSelect = useCallback(searchtype => setsearchtype(searchtype),[]);
  //console.log(searchtype)

  useEffect(() => {
    const fetchQuestion = async () => {
      const response = await fetch(`http://52.202.27.18:8080/question/`);
      if(!response.ok){
        throw new Error('Some Thing Went Error');
      }
      const data = await response.json();
      setQuestion(data.content);
    }
    fetchQuestion().catch((err) => {
      console.log(err)
    })
  },[]);

  return (
    <ContainerUI>
      <div>
        <Select categories={question} onSelect={onSelect} searchtype={searchtype}/>
      </div>
      <div>
        <ProblemList searchtype={searchtype}/>
      </div>
    </ContainerUI>
  );
};

export default Test;
