class DeleteLoyaltyRowManagment extends React.Component {
    constructor(props) {
      super(props);
      this.state = { selected: [0, 1] };
    }
  
    handleBtnClick = () => {
      if (!this.state.selected.includes(2)) {
        this.setState(() => ({
          selected: [...this.state.selected, 2]
        }));
      } else {
        this.setState(() => ({
          selected: this.state.selected.filter(x => x !== 2)
        }));
      }
    }
  
    handleOnSelect = (row, isSelect) => {
      if (isSelect) {
        this.setState(() => ({
          selected: [...this.state.selected, row.id]
        }));
      } else {
        this.setState(() => ({
          selected: this.state.selected.filter(x => x !== row.id)
        }));
      }
    }
  
    handleOnSelectAll = (isSelect, rows) => {
      const ids = rows.map(r => r.id);
      if (isSelect) {
        this.setState(() => ({
          selected: ids
        }));
      } else {
        this.setState(() => ({
          selected: []
        }));
      }
    }
  
    render() {
      const selectRow = {
        mode: 'checkbox',
        clickToSelect: true,
        selected: this.state.selected,
        onSelect: this.handleOnSelect,
        onSelectAll: this.handleOnSelectAll
      };
      return (
        <div>
          <button className="btn btn-success" onClick={ this.handleBtnClick }>Select/UnSelect 3rd row</button>
          <BootstrapTable keyField="id" data={ products } columns={ columns } selectRow={ selectRow } />
          <Code>{ sourceCode }</Code>
        </div>
      );
    }
  }