import { useState, useEffect } from "react";
import LoadingSpinner from "../../../UI/LoadingSpinner";

import SubmissionItem from "./SubmissionItem";
import useHttpRequest from "../../../../hook/use-http";

const SubmissionList = ({questionId}) => {

  const { isLoading, sendGetRequest} = useHttpRequest();
  const [submissionList, setSubmissionList] = useState([]);

  useEffect(() => {
    const submissionHandler=(data) => {
      setSubmissionList(data.data.content);
    }
    sendGetRequest(`/answer/solution/${questionId}/my`, submissionHandler);    
  }, [sendGetRequest, questionId])

  return (
    <>
      {isLoading && <LoadingSpinner />}
      {submissionList.map((item, index) => <SubmissionItem key={item.answerId} idx={+index+1} answerId={item.answerId} answer={item.answer} heartCnt={item.heartCnt} />)}
    </>
  )

}

export default SubmissionList;