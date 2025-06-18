// App.js
import React, { useState } from 'react';
import Greeting from './Greeting';

function App() {
  const [userName, setUserName] = useState('Chandan');

  return (
    <div>
      <Greeting name={userName} />
      
      {/* Optional: Change name dynamically */}
      <input
        type="text"
        value={userName}
        onChange={(e) => setUserName(e.target.value)}
        placeholder="Enter your name"
      />
    </div>
  );
}

export default App;
