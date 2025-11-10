# Unit Tests for Object-Oriented Design Examples

This directory contains comprehensive unit tests for the example code implementations.

## Running Tests

### Prerequisites

Install the required dependencies:

```bash
pip install -r ../requirements.txt
```

### Run All Tests

```bash
# From the tests directory
pytest -v

# Or from the project root
pytest tests/ -v
```

### Run Specific Test File

```bash
pytest tests/test_library_system.py -v
```

### Run with Coverage

```bash
pytest tests/ --cov=example-codes --cov-report=html
```

## Test Structure

### Library Management System Tests (`test_library_system.py`)

Comprehensive tests covering:

- **Address Class**: Creation and getter methods
- **Person Class**: Creation and attribute access
- **BookItem Class**: Book creation, status updates, checkout operations
- **Rack Class**: Location management
- **BookLending Class**: Lending operations and date tracking
- **BookReservation Class**: Reservation management
- **Fine Class**: Fine calculation and collection
- **Member Class**: Member operations, book checkout/return
- **Librarian Class**: Administrative operations
- **Library Class**: Complete library system integration

### Test Coverage

Current test coverage includes:
- ✅ 50+ unit tests
- ✅ All core classes and methods
- ✅ Edge cases and error handling
- ✅ Integration tests for complete workflows

## Adding New Tests

When adding tests for new modules:

1. Create a new test file: `test_<module_name>.py`
2. Import pytest and the module to test
3. Use fixtures for common test data
4. Follow the naming convention: `test_<functionality>`
5. Add docstrings to test classes and methods

Example:

```python
import pytest
from module import MyClass

@pytest.fixture
def sample_object():
    """Create a sample object for testing."""
    return MyClass("test")

class TestMyClass:
    """Test MyClass functionality."""
    
    def test_creation(self, sample_object):
        """Test object creation."""
        assert sample_object.get_name() == "test"
```

## Continuous Integration

These tests are designed to be run in CI/CD pipelines. See `.github/workflows/` for GitHub Actions configuration (if available).
