let testQuestion = '';
export const getTestQuestion = () => testQuestion;
export const setTestQuestion = (newTestQuestion) => testQuestion = newTestQuestion;

let answerList = [];
export const getAnswerList = () => answerList;
export const setAnswerList = (newAnswerList) => answerList = newAnswerList;

let testSolved = false;
export const getTestSolved = () => testSolved;
export const setTestSolved = (newTestSolved) => testSolved = newTestSolved;