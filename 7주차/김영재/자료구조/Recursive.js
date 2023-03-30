const basicFlag = [true, false];
const factorialFlag = [true, false];
const fibonacciFlag = [true, false];
const callStackDiv = [];
const fibonacciTag = [];
const factorialResult = {};
const fibonacciResult = {};
const exampleList = [
    { 
       id : 1 ,
       name : 'basicWrapper' ,
    },
    { 
       id : 2 ,
       name : 'factorialWrapper' ,
    },
    { 
       id : 3 ,
       name : 'fibonacciWrapper' ,
    },
];

const basicInput = () => {
    if(basicFlag[0]) {
        allRemoveChildren('basicInput');
        allRemoveChildren('basicOutput');
        
        disabledButton('basicInputButton');

        const call = (index) => {
            if(index == 10) {
                return;
            }
              
            call(++index);
            
            sleep(1000*(index))
            .then(() => {
                const div = createDivTag(`console.log(${index})`, "call-stack-box")
                addDescription('basicInput', div)
                callStackDiv.push(div);         
                if(index == 10) enabledButton('basicOutputButton');
                basicFlag[1] = true;
        
            });
        }   

        basicFlag[0] = false;
        call(0);
    }
}


const basicOutput = () => {
    if(basicFlag[1]) {
        basicFlag[1] = false;
        disabledButton('basicOutputButton');
        for(let i = 1; i <= 10; i++) {
            sleep(1000*i)
            .then(() => {
                callStackDiv.pop().remove();
                addDescription("basicOutput", createSpanTag(`${11-i} 출력`, "basic-ouput-p"));
                if(i == 10) {
                    enabledButton('basicInputButton');
                    basicFlag[0] = true;
                }
            })
        }
    }
}


const sleep = (ms) => new Promise((r) => setTimeout(r, ms));


const factorialInput = () => {
    if(factorialFlag[0]) {
        factorialFlag[0] = false;
        allRemoveChildren("factorialInput");
        allRemoveChildren("factorialOutput");
        
        disabledButton('factorialInputButton')

        const val = document.getElementById("factorial").value;

        const factorial = (n, max) => {
            sleep(1000*(max-n)).then(() => {
    
                let text = `${n} * factorial(${n-1})`;
                if(n === 1) text = '1';
                
                addDescription("factorialInput", createPTag(text, 'call-stack-box'));
            
                if(n == 1) {
                    enabledButton('factorialOutputButton')
                    factorialFlag[1] = true;
                }
            })
            if(n <= 1) {
                return 1;
            } 
            return n * factorial(n - 1, max);
        }

        factorialResult.result = factorial(val, val);
    }
}

const factorialOutput = () => {
    if(factorialFlag[1]) {
        factorialFlag[1] = false;
        disabledButton('factorialOutputButton')
     
        addDescription("fibonacciOutput", createPTag('연산중....', 'call-stack-box'));

        sleep(1000).then(() => {

            addDescription("factorialOutput", createPTag(`result = ${factorialResult.result}`, 'call-stack-box'));
        
            factorialFlag[0] = true;
        })
    }
}

const fibonacciInput = () => {
    if(fibonacciFlag[0]) {
        fibonacciFlag[0] = false;
        allRemoveChildren("fibonacciInput");
        allRemoveChildren("fibonacciOutput");

        disabledButton('fibonacciInputButton');
        
        const val = document.getElementById("fibonacci").value;
        
        
        const sleepCallback = (n) => {
            let text = `fibonacci(${n - 1}) +fibonacci(${n - 2})`;
                if(n < 2) text = `${n}`;
                
                const p = createPTag(text, 'call-stack-box');
                addDescription("fibonacciInput", p);
                fibonacciTag.push(p);
                if((fibonacciTag.length >= 1 && n == 0 || (fibonacciTag.length == 1 && n == 1))) {
                    enabledButton('fibonacciOutputButton');
                    fibonacciFlag[1] = true;
                }
        }
        
        const fibonacci = (n, max) => {
            if(fibonacciResult[n]) return fibonacciResult[n];

            if(n < 2) {
                sleep(1000*(max-n)).then(() => sleepCallback(n))

                fibonacciResult[n] = n;

                return n;
            }
            
            const result = fibonacci(n - 1, max) + fibonacci(n - 2, max);
                        
            fibonacciResult[n] = result;
            sleep(1000*(max-n)).then(() => sleepCallback(n))

            return result;
        }
        fibonacci(val, val);
        console.log(fibonacciResult);
    }

}

const fibonacciOutput = () => {
    console.log(fibonacciResult)
    if(fibonacciFlag[1]) {
        fibonacciFlag[1] = false;
        disabledButton('fibonacciOutputButton');
        const length = fibonacciTag.length;
        const keyList = Object.keys(fibonacciResult);
        const resultKey = keyList.length == 1 ? keyList[0] : length-1;
        const text = `result = ${fibonacciResult[resultKey]}`;
        addDescription("fibonacciOutput", createPTag('연산중....', 'call-stack-box'));
        for(let i = 0; i < length; i++) {
            fibonacciTag.pop().remove();
            
            if(fibonacciTag.length == 0) {
                sleep(1000).then(() => {
                    enabledButton('fibonacciInputButton');
                    fibonacciFlag[0] = true;
                    fibonacciFlag[1] = false;
                    keyList.forEach(key => delete fibonacciResult[key]);
                    addDescription("fibonacciOutput", createPTag(text, 'call-stack-box'));
                });
            }
        }
    }

}



const show = (exampleId) => {
    exampleList.filter(example => example.id !== exampleId)
                .forEach(example => document.getElementById(example.name).className = 'hide');
    document.getElementById(exampleList.find(example => example.id === exampleId).name).className = 'show';
}


const createPTag = (content, className) => {
    const pTag = document.createElement('p');
    pTag.innerHTML = content;
    pTag.className = className;
    return pTag;
}


const createSpanTag = (content, className) => {
    const pTag = document.createElement('span');
    pTag.innerHTML = content;
    pTag.className = className;
    return pTag;
}

const createDivTag = (content, className) => {
    const divTag = document.createElement('div');
    divTag.innerHTML = content;
    divTag.className = className;
    return divTag;
}

const addDescription = (id, element) => {
    document.getElementById(id).append(element);
}

const allRemoveChildren = (id) => {
    const element = document.getElementById(id);
    while(element.hasChildNodes()) {
        element.removeChild(element.firstChild);
    }
}

const disabledButton = (id) => document.getElementById(id).disabled = true;

const enabledButton = (id) => document.getElementById(id).disabled = false;