import './App.scss'
import {BrowserRouter as Router, Routes, Route} from "react-router-dom";
import BidPage from "./components/BidPage.tsx";

function App() {

    return (
        <div className="App">
            <Router>
                <div>
                    <Routes>
                        <Route path="/bid" exact element={<BidPage/>}/>
                    </Routes>
                </div>
            </Router>
        </div>
    );
}

export default App;
