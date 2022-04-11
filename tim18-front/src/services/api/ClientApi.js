
const fetchClients = async () => {
    const res = await fetch('http://localhost:5000/clients')

    const data = await res.json()

    return data
  }

  // Fetch Client 
  const fetchClient = async (id) => {
    const res = await fetch(`http://localhost:5000/clients/${id}`)

    const data = await res.json()

    return data
  }
  
// Add Client
const addClient = async (client) => {
  const res = await fetch('http://localhost:5000/clients', 
                          {method: 'POST',
                          headers: {
                            'Content-type' : 'application/json'
                          },
                          body: JSON.stringify(client)
                          })
  const data =await res.json()
  return data
  //setClients([...clients, data])
      
}

  // Delete client
const deleteClient = async (id) => {

  await fetch(`http://localhost:5000/clients/${id}`, {method: 'DELETE'})

  // UI deletation
  //setClients(clients.filter((client) => client.id !== id))
}

//
const UpdatePassword = async (id, password) => {

  const client = await fetchClient(id)
  const updatedClient = {...client, password: password}//an event
  const res = await fetch(`http://localhost:5000/clients/${id}`, {method: 'PUT',
                                                                headers: {
                                                                  'Content-type': 'application/json'
                                                                },
                                                                body: JSON.stringify(updatedClient)
                                                               })

  const data = await res.json()
  return data
  /*setClients(clients.map(
    (client)=> client.id ===id ? 
      {...client, reminder: data.reminder} : 
      client
    ))*/
}